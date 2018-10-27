package com.payslip.tax.calculation.chain;

public class Upto37kTaxCalculator implements ITaxCalculatorChain {
  @Override
  public void setNextElementInChain(ITaxCalculatorChain nextElementInChain) {
    
  }

  @Override
  public Double calculateTax(double amount) {
    return null;
  }
}
