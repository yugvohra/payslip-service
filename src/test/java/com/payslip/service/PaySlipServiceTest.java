package com.payslip.service;

import com.payslip.domain.Employee;
import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaySlipServiceTest {

  @Test
  public void shouldGeneratePaySlip() {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000d, 0.10d, 1000d, "November");
    Employee employee=new Employee("john","dave",payAttribute);
    PaySlipService paySlipService = new PaySlipService();
    Long computedTax = 3000L;
    PaySlip expectedPaySlip = new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("November").withGrossIncome(10000L).
                              withIncomeTax( 3000L).withNetIncome( 7000L).withSuperAmount( 1000L).build();

    //when
    PaySlip generatedPaySlip = paySlipService.generatePayslip(employee, computedTax);
    //then
    assertThat(generatedPaySlip).isEqualToComparingFieldByField(expectedPaySlip);
  }

}
