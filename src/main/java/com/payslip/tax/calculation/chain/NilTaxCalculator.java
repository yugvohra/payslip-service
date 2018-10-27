package com.payslip.tax.calculation.chain;

import com.payslip.tax.calculation.helper.TaxCaluclatorHelper;

public class NilTaxCalculator implements ITaxCalculatorChain {
  private static NilTaxCalculator INSTANCE = new NilTaxCalculator();
  private final Double lowerLimit;
  private final Double upperLimit;
  private final Double taxRate;
  private ITaxCalculatorChain nextElement;

  private NilTaxCalculator() {
    this.lowerLimit = 0d;
    this.upperLimit = 180000d;
    this.taxRate = 0d;
  }

  public static NilTaxCalculator getInstance() {
    return INSTANCE;
  }

  @Override
  public void setNextElementInChain(ITaxCalculatorChain nextElementInChain) {
    this.nextElement = nextElementInChain;
  }

  @Override
  public Double calculateTax(double amount) {
    double taxableAmount = getTaxableAmount(amount);
    return TaxCaluclatorHelper.calculateTax(taxableAmount, taxRate);
  }

  private double getTaxableAmount(double amount) {
    double taxableAmount = amount >= upperLimit ? upperLimit : amount;
    taxableAmount -= lowerLimit;
    return taxableAmount;
  }

}
