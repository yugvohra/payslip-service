package com.payslip.tax.calculation.chain.calculators;

public interface ITaxCalculatorChain {
  void setNextElementInChain(ITaxCalculatorChain nextElementInChain);

  Double calculateTax(double amount);
}
