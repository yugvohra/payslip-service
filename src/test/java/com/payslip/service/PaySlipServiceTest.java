package com.payslip.service;

import com.payslip.domain.Employee;
import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaySlipServiceTest {

  private PaySlipService paySlipService;

  @Before
  public void setUp() {
    paySlipService = PaySlipService.getInstance();
  }

  @Test
  public void shouldGeneratePaySlip() {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000d, 0.10d, 1000d, "2018-11-01");
    Employee employee = new Employee("john", "dave", payAttribute);
    Long computedTax = 3000L;
    PaySlip expectedPaySlip = new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("1 NOVEMBER - 30 NOVEMBER").withGrossIncome(10000L).
        withIncomeTax(3000L).withNetIncome(7000L).withSuperAmount(1000L).build();

    //when
    PaySlip generatedPaySlip = paySlipService.generatePayslip(employee, computedTax);
    //then
    assertThat(generatedPaySlip).isEqualToComparingFieldByField(expectedPaySlip);
  }


  @Test
  public void shouldGeneratePaySlipForLessThanAMonth() {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000d, 0.10d, 1000d, "2018-11-16");
    Employee employee = new Employee("john", "dave", payAttribute);
    Long computedTax = 3000L;
    PaySlip expectedPaySlip = new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("16 NOVEMBER - 30 NOVEMBER").withGrossIncome(5000L).
        withIncomeTax(1500L).withNetIncome(3500L).withSuperAmount(500L).build();

    //when
    PaySlip generatedPaySlip = paySlipService.generatePayslip(employee, computedTax);
    //then
    assertThat(generatedPaySlip).isEqualToComparingFieldByField(expectedPaySlip);
  }

  @Test
  public void shouldRoundOffGrossIncomeBeforeGenerationOfPaySlip() {
    //given
    EmployeePay payAttribute = new EmployeePay(120000d, 10000.68d, 0.10d, 1000d, "2018-11-01");
    Employee employee = new Employee("john", "dave", payAttribute);
    Long computedTax = 3000L;
    PaySlip expectedPaySlip = new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("1 NOVEMBER - 30 NOVEMBER").withGrossIncome(10001L).
        withIncomeTax(3000L).withNetIncome(7001L).withSuperAmount(1000L).build();

    //when
    PaySlip generatedPaySlip = paySlipService.generatePayslip(employee, computedTax);
    //then
    assertThat(generatedPaySlip).isEqualToComparingFieldByField(expectedPaySlip);
  }

}
