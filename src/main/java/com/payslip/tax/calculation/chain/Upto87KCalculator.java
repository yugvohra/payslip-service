package com.payslip.tax.calculation.chain;

public class Upto87KCalculator extends AbstractTaxCalculator {
  @Override
  protected double getTaxableAmount(double amount) {
    return 0;
  }

  @Override
  protected double getTaxRate() {
    return 0;
  }
}
