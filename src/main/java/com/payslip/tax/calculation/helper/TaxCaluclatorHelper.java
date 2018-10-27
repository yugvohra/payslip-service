package com.payslip.tax.calculation.helper;

public class TaxCaluclatorHelper {

  public static Double calculateTax(Double amount,Double taxRate)
  {
    return amount*taxRate;
  }
}
