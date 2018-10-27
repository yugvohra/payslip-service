package com.payslip.helper;

import com.payslip.domain.EmployeePay;

/**
 * This could be moved to payService if pay information is not required anywhere in system
 */
public class PayHelper {

  public EmployeePay fetchPayAttributeFor(Double aSalary, Double superRate) {
    Double grossIncome = aSalary / 12;
    Double superAmount = grossIncome * superRate;
    return new EmployeePay(aSalary, grossIncome, superRate, superAmount);
  }

}
