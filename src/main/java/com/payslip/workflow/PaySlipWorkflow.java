package com.payslip.workflow;

import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import com.payslip.service.PaySlipService;
import com.payslip.service.TaxService;

/**
 * Rudimentary workflow
 */
public class PaySlipWorkflow {
  private final PaySlipService paySlipService;
  private final TaxService taxService;

  public PaySlipWorkflow(PaySlipService paySlipService, TaxService taxService) {
    this.paySlipService = paySlipService;
    this.taxService = taxService;
  }

  public PaySlip generatePayslip(EmployeePay pay) {
    Long computedTax = taxService.getRoundedOffTax(pay.getAnnualSalary());
    return paySlipService.generatePayslip(pay, computedTax);
  }

}
