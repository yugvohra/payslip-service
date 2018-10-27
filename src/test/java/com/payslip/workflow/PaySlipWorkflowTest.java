package com.payslip.workflow;

import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
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
public class PaySlipWorkflowTest {
  private PaySlipWorkflow paySlipWorkflow;
  @Mock
  private TaxService taxService;
  @Mock
  private PaySlipService paySlipService;

  @Before
  public void setUp() {
    paySlipWorkflow = new PaySlipWorkflow(paySlipService, taxService);
  }

  @Test
  public void shouldGeneratePayslipForTaxableIncome() {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000d, 0.10d, 1000d);
    PaySlip paySlip = new PaySlip(10000L, 3000L, 7000L, 1000L);

    ArgumentCaptor<Long> calTaxArgumentCaptor = ArgumentCaptor.forClass(Long.class);
    ArgumentCaptor<Double> salForTaxArrgumentCaptor = ArgumentCaptor.forClass(Double.class);

    when(taxService.getRoundedOffTax(anyDouble())).thenReturn(3000L);
    when(paySlipService.generatePayslip(any(), any())).thenReturn(paySlip);

    //when
    paySlipWorkflow.generatePayslip(payAttribute);

    //then
    //the workflow just stitch together the services and only their invocations need to be checked
    verify(taxService, times(1)).getRoundedOffTax(salForTaxArrgumentCaptor.capture());
    verify(paySlipService, times(1)).generatePayslip(any(), calTaxArgumentCaptor.capture());
    assertThat(calTaxArgumentCaptor.getValue()).isEqualTo(3000L);
    assertThat(salForTaxArrgumentCaptor.getValue()).isEqualTo(120000d);
  }
}
