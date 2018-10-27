package com.payslip.service;

import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaySlipServiceTest {

  @Test
  public void shouldGeneratePaySlip() {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000d, 0.10d, 1000d);
    PaySlipService paySlipService = new PaySlipService();
    Long computedTax = 3000L;
    PaySlip expectedPaySlip = new PaySlip(10000L, 3000L, 7000L, 1000L);

    //when
    PaySlip generatedPaySlip = paySlipService.generatePayslip(payAttribute, computedTax);
    //then
    assertThat(generatedPaySlip).isEqualToComparingFieldByField(expectedPaySlip);
  }

}
