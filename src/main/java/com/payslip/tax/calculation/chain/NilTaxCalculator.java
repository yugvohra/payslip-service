package com.payslip.tax.calculation.chain;

public class NilTaxCalculator extends AbstractTaxCalculator {
  private static NilTaxCalculator INSTANCE = new NilTaxCalculator();
  private final Double lowerLimit;
  private final Double upperLimit;
  private final Double taxRate;

  private NilTaxCalculator() {
    this.lowerLimit = 0d;
    this.upperLimit = 180000d;
    this.taxRate = 0d;
  }

  public static NilTaxCalculator getInstance() {
    return INSTANCE;
  }

  @Override
  public double getTaxableAmount(double amount) {
    double taxableAmount = amount >= upperLimit ? upperLimit : amount;
    taxableAmount -= lowerLimit;
    return taxableAmount;
  }

  @Override
  protected double getTaxRate() {
    return this.getTaxRate();
  }

}
