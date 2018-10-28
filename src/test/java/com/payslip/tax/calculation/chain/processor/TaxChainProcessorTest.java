package com.payslip.tax.calculation.chain.processor;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxChainProcessorTest {

  private TaxChainProcessor taxChainProcessor;

  @Before
  public void setUp() {
    taxChainProcessor = TaxChainProcessor.getInstance();
  }

  @Test
  public void shouldReturnZeroTaxForLowestSlab() {
    //given
    Double amount = 1700d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0d);
  }

  @Test
  public void shouldReturnZeroTaxForBoundaryOfZeroSlab() {
    //given
    Double amount = 18000d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0d);
  }

  @Test
  public void shouldReturnTaxForLowerBoundaryOf37kSlab() {
    //given
    Double amount = 18201d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(0.19d);
  }

  @Test
  public void shouldReturnTaxFor37kSlab() {
    //given
    Double amount = 28200d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(1900d);
  }

  @Test
  public void shouldReturnTaxForUpperBoundaryOf37kSlab() {
    //given
    Double amount = 37000d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(3572d);
  }

  @Test
  public void shouldReturnAccumulatedTaxForLowerBoundaryOf87kSlab() {
    //given
    Double amount = 37001d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(3572.325d);
  }

  @Test
  public void shouldReturnAccumulatedTaxFor87kSlab() {
    //given
    Double amount = 47000d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(6822d);
  }

  @Test
  public void shouldReturnTaxForUpperBoundaryOf87kSlab() {
    //given
    Double amount = 87000d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(19822d);
  }

  @Test
  public void shouldReturnTaxForLowerBoundaryOf180kSlab() {
    //given
    Double amount = 87001d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(19822.37d);
  }

  @Test
  public void shouldReturnTaxFor180kSlab() {
    //given
    Double amount = 97000d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(23522d);
  }

  @Test
  public void shouldReturnTaxForUpperBoundaryOf180kSlab() {
    //given
    Double amount = 180000d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(54232d);
  }


  @Test
  public void shouldReturnTaxForlowerBoundaryOfMaxSlab() {
    //given
    Double amount = 180001d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(54232.45d);
  }

  @Test
  public void shouldReturnTaxForMaxSlab() {
    //given
    Double amount = 190000d;
    //when
    Double calculatedTax = taxChainProcessor.getCalculatedTax(amount);

    //then
    assertThat(calculatedTax).isEqualTo(58732d);
  }
}
