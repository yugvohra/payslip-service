package com.payslip.tax.calculation.chain.calculators.impl;

import com.payslip.tax.calculation.chain.calculators.AbstractTaxCalculator;

public class MaxTaxCalculator extends AbstractTaxCalculator {

  private final Double lowerLimit;
  private final Double taxRate;

  public MaxTaxCalculator(Double lowerLimit, Double taxRate) {
    this.lowerLimit = lowerLimit;
    this.taxRate = taxRate;
  }

  @Override
  protected double getTaxableAmount(double amount) {
    double taxableAmount = amount;
    taxableAmount -= lowerLimit;
    //boundary check for slab is +1 then the actual boundary
    return taxableAmount >= 0 ? taxableAmount + 1 : 0d;
  }

  @Override
  protected double getTaxRate() {
    return this.taxRate;
  }
}
