package com.payslip.domain;

public class PaySlip {
  private final Long grossIncome;
  private final Long incomeTax;
  private final Long netIncome;
  private final Long superAmount;

  public PaySlip(Long grossIncome, Long incomeTax, Long netIncome, Long superAmount) {
    this.grossIncome = grossIncome;
    this.incomeTax = incomeTax;
    this.netIncome = netIncome;
    this.superAmount = superAmount;
  }

  public Long getGrossIncome() {
    return grossIncome;
  }

  public Long getIncomeTax() {
    return incomeTax;
  }

  public Long getNetIncome() {
    return netIncome;
  }

  public Long getSuperAmount() {
    return superAmount;
  }

}
