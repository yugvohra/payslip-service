package com.payslip.tax.calculation.chain;

import com.payslip.tax.calculation.chain.calculators.impl.IncomeSlabTaxCalculator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncomeSlabTaxCalculatorTest {

  private final IncomeSlabTaxCalculator taxCalculator = new IncomeSlabTaxCalculator(18201d,37000d,0.19d);

  @Test
  public void shouldReturnZeroTaxForAmountBelowLimit() {
    //given
    Double amount = 18200d;
    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0d);
  }


  @Test
  public void shouldReturnTaxForAmountAtLeftBoundary() {
    //given
    Double amount = 18201d;
    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0.19d);
  }


  @Test
  public void shouldReturnMaxTaxForAmountAtRightBoundary() {
    //given
    Double amount = 37000d;
    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(3572d);
  }


  @Test
  public void shouldReturnTaxForAmountWithinSlabRange() {
    //given
    Double amount = 28200d;
    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(1900d);
  }


}
