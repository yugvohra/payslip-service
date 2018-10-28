package com.payslip.tax.calculation.chain;

import com.payslip.tax.calculation.chain.calculators.AbstractTaxCalculator;
import com.payslip.tax.calculation.chain.calculators.impl.MaxTaxCalculator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxTaxCalculatorTest {

  private AbstractTaxCalculator taxCalculator = new MaxTaxCalculator(180001d, 0.45d);

  @Test
  public void shouldReturnZeroTaxForAmountBelowLimit() {
    //given
    Double amount = 180000d;
    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0d);
  }


  @Test
  public void shouldReturnTaxForAmountAtLeftBoundary() {
    //given
    Double amount = 180001d;
    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0.45d);
  }


  @Test
  public void shouldReturnTaxForAmountWithinSlabRange() {
    //given
    Double amount = 190000d;
    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(4500d);
  }


}
