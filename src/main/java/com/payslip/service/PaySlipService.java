package com.payslip.service;

import com.payslip.domain.Employee;
import com.payslip.domain.PaySlip;

import static java.lang.Math.round;

public class PaySlipService {

  public PaySlip generatePayslip(Employee employee, Long computedTax) {
    double netIncome = employee.getEmployeePay().getGrossIncome() - computedTax;
    return new PaySlip.PaySlipBuilder().withFirstName(employee.getFirstName()).withLastName(employee.getLastName()).withMonthDate(employee.getEmployeePay().getPaymentDate()).withGrossIncome(round(employee.getEmployeePay().getGrossIncome())).
        withIncomeTax(computedTax).withNetIncome(round(netIncome)).withSuperAmount(round(employee.getEmployeePay().getSuperAmount())).build();
  }

}
