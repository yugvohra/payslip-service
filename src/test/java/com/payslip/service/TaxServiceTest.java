package com.payslip.service;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxServiceTest {
  private TaxService taxService;

  @Before
  public void setUp() {
    taxService = TaxService.getInstance();
  }

  @Test
  public void shouldCalculateTaxOnAnIncome() {
    //given
    Double salary = 180000d;
    //when
    Long calculatedTax = taxService.getRoundedOffTaxForMonth(salary);
    //then
    assertThat(calculatedTax).isEqualTo(4519L);
  }
}
