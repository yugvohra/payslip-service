package com.payslip.tax.calculation.chain;

import com.payslip.tax.calculation.helper.TaxCaluclatorHelper;

public abstract class AbstractTaxCalculator implements ITaxCalculatorChain {
  private ITaxCalculatorChain nextElement;


  @Override
  public void setNextElementInChain(ITaxCalculatorChain nextElementInChain) {
    this.nextElement = nextElementInChain;
  }

  @Override
  public Double calculateTax(double amount) {
    Double taxableAmount = getTaxableAmount(amount);
    Double calculatedTax = TaxCaluclatorHelper.calculateTax(taxableAmount, getTaxRate());
    return nextElement == null ? calculatedTax : calculatedTax + nextElement.calculateTax(amount);
  }

  protected abstract double getTaxableAmount(double amount);

  protected abstract double getTaxRate();
}
