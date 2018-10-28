package com.payslip.facade;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.payslip.domain.Employee;
import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import com.payslip.service.CSVService;
import com.payslip.service.PaySlipService;
import com.payslip.service.TaxService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaySlipWorkflowFacadeTest {
  private PaySlipWorkflowFacade subject;
  @Mock
  private TaxService taxService;
  @Mock
  private PaySlipService paySlipService;
  @Mock
  private PrintStream printStream;
  @Mock
  private CSVService csvService;

  @Before
  public void setUp() {
    subject = new PaySlipWorkflowFacade();
    Whitebox.setInternalState(subject,"taxService",taxService);
    Whitebox.setInternalState(subject,"paySlipService",paySlipService);
    Whitebox.setInternalState(subject,"csvService",csvService);
    System.setOut(printStream);

  }

  @Test
  public void shouldGeneratePayslipForTaxableIncome() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000d, 10d, 1000d, "1 NOVEMBER - 30 NOVEMBER");
    Employee employee=new Employee("john","dave",payAttribute);

    PaySlip paySlip = new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("1 NOVEMBER - 30 NOVEMBER").withGrossIncome(10000L).
        withIncomeTax( 3000L).withNetIncome( 7000L).withSuperAmount( 1000L).build();

    when(csvService.fetchEmployeeFrom(anyString())).thenReturn(Lists.newArrayList(employee));
    when(taxService.getRoundedOffTaxForMonth(anyDouble())).thenReturn(3000L);
    when(paySlipService.generatePayslip(any(),anyLong())).thenReturn(paySlip);

    ArgumentCaptor<Long> roundedTaxCaptor = ArgumentCaptor.forClass(Long.class);
    ArgumentCaptor<Double> taxableIncomeCaptor = ArgumentCaptor.forClass(Double.class);
    ArgumentCaptor<List> payslipsArgCaptor=ArgumentCaptor.forClass(List.class);


    //when
    subject.execute("sample.csv");

    //then
    //the facade just stitch together the services and only their invocations need to be checked
    //verify the calls
    verify(taxService, times(1)).getRoundedOffTaxForMonth(taxableIncomeCaptor.capture());
    verify(paySlipService, times(1)).generatePayslip(any(), roundedTaxCaptor.capture());
    verify(csvService,times(1)).fetchEmployeeFrom(any());
    verify(csvService,times(1)).writePaySlipsToCsv(payslipsArgCaptor.capture());

    //assert the values
    assertThat(roundedTaxCaptor.getValue()).isEqualTo(3000L);
    assertThat(taxableIncomeCaptor.getValue()).isEqualTo(120000d);
    assertThat(payslipsArgCaptor.getValue()).hasSize(1);
    assertThat(payslipsArgCaptor.getValue().get(0)).isEqualToComparingFieldByField(paySlip);
  }
}
