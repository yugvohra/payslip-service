package com.payslip.tax.calculation.chain;

public class Upto37kTaxCalculator extends AbstractTaxCalculator {

  private static final Upto37kTaxCalculator INSTANCE = new Upto37kTaxCalculator();
  private final Double lowerLimit;
  private final Double upperLimit;
  private final Double taxRate;

  private Upto37kTaxCalculator() {
    this.lowerLimit = 18200d;
    this.upperLimit = 37000d;
    this.taxRate = 0.19d;
  }

  public static Upto37kTaxCalculator getInstance() {
    return INSTANCE;
  }


  @Override
  protected double getTaxableAmount(double amount) {
    double taxableAmount = amount >= upperLimit ? upperLimit : amount;
    taxableAmount -= lowerLimit;
    //boundary check for each slab
    return taxableAmount >= 1 ? taxableAmount : 0d;
  }

  @Override
  protected double getTaxRate() {
    return this.taxRate;
  }
}
