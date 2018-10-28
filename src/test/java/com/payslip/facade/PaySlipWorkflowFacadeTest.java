package com.payslip.facade;

import com.payslip.domain.Employee;
import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import com.payslip.service.CSVService;
import com.payslip.service.PaySlipService;
import com.payslip.service.TaxService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaySlipWorkflowFacadeTest {
  private PaySlipWorkflowFacade paySlipWorkflowFacade;
  @Mock
  private TaxService taxService;
  @Mock
  private PaySlipService paySlipService;
  @Mock
  private CSVService csvService;

  @Before
  public void setUp() {
    paySlipWorkflowFacade = new PaySlipWorkflowFacade();

  }

  @Test
  public void shouldGeneratePayslipForTaxableIncome() {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000d, 0.10d, 1000d, "November");
    Employee employee=new Employee("john","dave",payAttribute);
    PaySlip paySlip = new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("November").withGrossIncome(10000L).
        withIncomeTax( 3000L).withNetIncome( 7000L).withSuperAmount( 1000L).build();


    ArgumentCaptor<Long> calTaxArgumentCaptor = ArgumentCaptor.forClass(Long.class);
    ArgumentCaptor<Double> salForTaxArrgumentCaptor = ArgumentCaptor.forClass(Double.class);

    when(taxService.getRoundedOffTaxForMonth(anyDouble())).thenReturn(3000L);
    when(paySlipService.generatePayslip(any(), any())).thenReturn(paySlip);

    //when
    paySlipWorkflowFacade.generatePayslip(employee);

    //then
    //the facade just stitch together the services and only their invocations need to be checked
    verify(taxService, times(1)).getRoundedOffTaxForMonth(salForTaxArrgumentCaptor.capture());
    verify(paySlipService, times(1)).generatePayslip(any(), calTaxArgumentCaptor.capture());
    assertThat(calTaxArgumentCaptor.getValue()).isEqualTo(3000L);
    assertThat(salForTaxArrgumentCaptor.getValue()).isEqualTo(120000d);
  }
}
