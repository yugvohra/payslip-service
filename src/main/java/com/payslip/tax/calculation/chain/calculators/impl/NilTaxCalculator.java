package com.payslip.tax.calculation.chain.calculators.impl;

import com.payslip.tax.calculation.chain.calculators.AbstractTaxCalculator;

public class NilTaxCalculator extends AbstractTaxCalculator {

  private final Double upperLimit;

  public NilTaxCalculator(Double upperLimit) {
    this.upperLimit = upperLimit;
  }

  @Override
  public double getTaxableAmount(double amount) {
    return amount >= upperLimit ? upperLimit : amount;
  }

  @Override
  protected double getTaxRate() {
    return 0d;
  }

}
