package com.payslip.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.payslip.domain.Employee;
import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import com.payslip.io.csv.CsvIOExecutor;
import com.payslip.io.csv.EmployeeCSVBean;
import com.payslip.io.csv.PaySlipCsvBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CSVServiceTest {
  @Mock
  private CsvIOExecutor csvIOExecutor;
  private CSVService subject;
  private String filePath;

  @Before
  public void setUp() {
    subject = new CSVService();
    Whitebox.setInternalState(subject, "csvIOExecutor", csvIOExecutor);
    filePath = "sample.csv";
  }

  @Test
  public void shouldReturnEmployeeObject() throws IOException {
    //given
    List csvBeans = Arrays.asList(new EmployeeCSVBean("john", "dave", 100000d, 0.10d, "April"));
    when(csvIOExecutor.fetchEmployeeBeans(anyString(), any())).thenReturn(csvBeans);
    Employee expectedEmployee = new Employee("john", "dave", EmployeePay.from(100000d, 0.10d, "April"));
    //when
    List<Employee> employees = subject.fetchEmployeeFrom(filePath);
    //then
    assertThat(employees).hasSize(1);
    assertThat(employees.get(0)).isEqualToComparingFieldByField(expectedEmployee);
  }

  @Test
  public void shouldTransformBeansToCsv() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
    //given
    List<PaySlip> payslips = Arrays.asList(new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("November").withGrossIncome(10000L).
        withIncomeTax(3000L).withNetIncome(7000L).withSuperAmount(1000L).build());
    doNothing().when(csvIOExecutor).writeBeanToCsv(any(), anyList());

    PaySlipCsvBean expectedCsvBean = new PaySlipCsvBean.Builder().withFirstName("john").withLastName("dave").withPaymentDate("November").withgrossIncome(10000L).
        withIncomeTax(3000L).withNetIncome(7000L).withSuperAmount(1000L).build();
    ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
    //when
    subject.writePaySlipsToCsv(payslips);
    //then
    verify(csvIOExecutor, times(1)).writeBeanToCsv(any(), argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().get(0)).isEqualToComparingFieldByField(expectedCsvBean);

  }

}
