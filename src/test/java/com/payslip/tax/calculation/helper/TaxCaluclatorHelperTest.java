package com.payslip.tax.calculation.helper;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxCaluclatorHelperTest {

  @Test
  public void shouldReturnZeroForZeroTaxRate() {
    //given
    Double amount = 1000d;
    Double taxRate = 0d;

    //when
    Double calculatedTax = TaxCaluclatorHelper.calculateTax(amount, taxRate);

    //then
    assertThat(calculatedTax).isEqualTo(0D);
  }

  @Test
  public void shouldReturnCorrectValueForTaxRate() {
    //given
    Double amount = 18800d;
    Double taxRate = 0.19d;

    //when
    Double calculatedTax = TaxCaluclatorHelper.calculateTax(amount, taxRate);

    //then
    assertThat(calculatedTax).isEqualTo(3572d);
  }
}
