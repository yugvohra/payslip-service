package com.payslip.service;

import com.payslip.tax.calculation.chain.processor.TaxChainProcessor;

import static java.lang.Math.round;

/*candidate for Dependency Injections */
public class TaxService {

  private static TaxService INSTANCE =new TaxService();
  private final TaxChainProcessor taxChainProcessor;

  private TaxService() {
    taxChainProcessor = TaxChainProcessor.getInstance();
  }

  public static TaxService getInstance() {
    return INSTANCE;
  }

  public Long getRoundedOffTaxForMonth(Double aTaxableIncome) {
    return round(taxChainProcessor.getCalculatedTax(aTaxableIncome) / 12);
  }
}
