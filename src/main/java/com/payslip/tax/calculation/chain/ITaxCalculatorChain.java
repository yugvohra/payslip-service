package com.payslip.tax.calculation.chain;

public interface ITaxCalculatorChain {
  void setNextElementInChain(ITaxCalculatorChain nextElementInChain);

  Double calculateTax(double amount);
}
