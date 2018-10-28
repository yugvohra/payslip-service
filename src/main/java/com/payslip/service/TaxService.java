package com.payslip.service;

import com.payslip.tax.calculation.chain.processor.TaxChainProcessor;

import static java.lang.Math.round;

/*candidate for Dependency Injections */
public class TaxService {

  private final TaxChainProcessor taxChainProcessor;

  public TaxService() {
    taxChainProcessor = TaxChainProcessor.getInstance();
  }

  public Long getRoundedOffTaxForMonth(Double aTaxableIncome) {
    return round(taxChainProcessor.getCalculatedTax(aTaxableIncome) / 12);
  }
}
