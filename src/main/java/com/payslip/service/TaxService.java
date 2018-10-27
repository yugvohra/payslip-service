package com.payslip.service;

import com.payslip.tax.calculation.chain.processor.TaxChainProcessor;
import com.payslip.tax.calculation.helper.TaxCaluclatorHelper;

/*candidate for Dependency Injections */
public class TaxService {

  private final TaxChainProcessor taxChainProcessor;

  public TaxService() {
    taxChainProcessor = TaxChainProcessor.getInstance();
  }

  public Long getRoundedOffTax(Double aTaxableIncome)
  {
    return taxChainProcessor.getGalculatedTaxRoundOffToInteger(aTaxableIncome);
  }
}
