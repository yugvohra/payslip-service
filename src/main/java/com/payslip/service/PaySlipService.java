package com.payslip.service;

import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;

import static java.lang.Math.round;

public class PaySlipService {

  public PaySlip generatePayslip(EmployeePay employeePay, Long computedTax)
  {
    Double netIncome= employeePay.getGrossIncome()-computedTax;
    return new PaySlip(round(employeePay.getGrossIncome()),computedTax,round(netIncome),round(employeePay.getSuperAmount()));
  }

}
