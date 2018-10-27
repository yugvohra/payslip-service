package com.payslip.tax.calculation.chain.calculators.impl;

import com.payslip.tax.calculation.chain.calculators.AbstractTaxCalculator;

/**
 * This class is responsible for calculating income tax for different slabs of income
 */
public class IncomeSlabTaxCalculator extends AbstractTaxCalculator {


  private final Double lowerLimit;
  private final Double upperLimit;
  private final Double taxRate;

  public IncomeSlabTaxCalculator(Double lowerLimit, Double upperLimit, Double taxRate) {
    this.lowerLimit = lowerLimit;
    this.upperLimit = upperLimit;
    this.taxRate = taxRate;
  }


  @Override
  protected double getTaxableAmount(double amount) {
    double taxableAmount = amount >= upperLimit ? upperLimit : amount;
    taxableAmount -= lowerLimit;
    //boundary check for each slab is + 1 then the actual boundary
    return taxableAmount >= 0 ? taxableAmount + 1 : 0d;
  }

  @Override
  protected double getTaxRate() {
    return this.taxRate;
  }
}
