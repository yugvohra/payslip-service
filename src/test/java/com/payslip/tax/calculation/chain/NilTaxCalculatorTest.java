package com.payslip.tax.calculation.chain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NilTaxCalculatorTest {

  private NilTaxCalculator taxCalculator;

  @Before
  public void setUp() {
    taxCalculator = NilTaxCalculator.getInstance();
  }

  @Test
  public void shouldReturnZeroTax() {
    //given
    Double amount = 70000d;

    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0d);
  }

  @Test
  public void shouldReturnZeroTaxForUpperLimitAndBeyond() {
    //given
    Double amount = 188000d;

    //when
    Double calculatedTax = taxCalculator.calculateTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0d);
  }

}
