package com.payslip.service;

import com.payslip.domain.EmployeePay;
import com.payslip.helper.PayHelper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PayHelperTest {

  @Test
  public void shouldGeneratePayAttributes() {
    //given
    PayHelper payHelper = new PayHelper();
    Double aSalary = 12000d;
    Double superRate = 0.10d;
    EmployeePay expectedAttribute = new EmployeePay(12000d, 1000d, 0.10d, 100d);

    //when
    EmployeePay employeePay = payHelper.fetchPayAttributeFor(aSalary, superRate);
    //then
    assertThat(employeePay).isEqualToComparingFieldByField(expectedAttribute);
  }

}
