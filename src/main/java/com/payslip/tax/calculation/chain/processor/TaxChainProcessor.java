package com.payslip.tax.calculation.chain.processor;

import com.payslip.tax.calculation.chain.calculators.ITaxCalculatorChain;
import com.payslip.tax.calculation.chain.calculators.impl.IncomeSlabTaxCalculator;
import com.payslip.tax.calculation.chain.calculators.impl.MaxTaxCalculator;
import com.payslip.tax.calculation.chain.calculators.impl.NilTaxCalculator;

public class TaxChainProcessor {
  private static TaxChainProcessor INSTANCE = new TaxChainProcessor();
  private ITaxCalculatorChain chain;

  private TaxChainProcessor() {
    initialize();
  }

  public static TaxChainProcessor getInstance() {
    return INSTANCE;
  }

  /**Initialize chain of tax calculators for different slabs */
  private void initialize() {
    ITaxCalculatorChain nillTaxCalculator = new NilTaxCalculator(18200d);
    ITaxCalculatorChain upto37KTaxCalculator = new IncomeSlabTaxCalculator(18201d, 37000d, 0.19d);
    ITaxCalculatorChain upto87KTaxCalculator = new IncomeSlabTaxCalculator(37001d, 87000d, 0.325d);
    ITaxCalculatorChain upto180KTaxCalculator = new IncomeSlabTaxCalculator(87001d, 180000d, 0.37d);
    ITaxCalculatorChain maxTaxCalculator = new MaxTaxCalculator(180001d, 0.45d);

    /* Initializing the chain*/
    maxTaxCalculator.setNextElementInChain(upto180KTaxCalculator);
    upto180KTaxCalculator.setNextElementInChain(upto87KTaxCalculator);
    upto87KTaxCalculator.setNextElementInChain(upto37KTaxCalculator);
    upto37KTaxCalculator.setNextElementInChain(nillTaxCalculator);
    chain = maxTaxCalculator;
  }

  /**
   * @param amount on which tax needs to be computed
   * @return computed tax
   */

  public double getCalculatedTax(Double amount) {
    return chain.calculateTax(amount);
  }

}
