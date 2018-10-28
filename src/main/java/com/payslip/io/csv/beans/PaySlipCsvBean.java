package com.payslip.io.csv.beans;

import com.opencsv.bean.CsvBindByName;

public class PaySlipCsvBean {
  @CsvBindByName(column = "first name")
  private String firstName;
  @CsvBindByName(column = "last name")
  private String lastName;
  @CsvBindByName(column = "gross income")
  private Long grossIncome;
  @CsvBindByName(column = "income tax")
  private Long incomeTax;
  @CsvBindByName(column = "net income")
  private Long netIncome;
  @CsvBindByName(column = "super")
  private Long superAmount;
  @CsvBindByName(column = "payment date")
  private String paymentDate;

  public PaySlipCsvBean(String firstName, String lastName, String paymentDate, Long grossIncome, Long incomeTax, Long netIncome, Long superAmount) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.paymentDate = paymentDate;
    this.grossIncome = grossIncome;
    this.incomeTax = incomeTax;
    this.netIncome = netIncome;
    this.superAmount = superAmount;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPaymentDate() {
    return paymentDate;
  }

  public Long getGrossIncome() {
    return grossIncome;
  }

  public Long getIncomeTax() {
    return incomeTax;
  }

  public Long getNetIncome() {
    return netIncome;
  }

  public Long getSuperAmount() {
    return superAmount;
  }

  public static class Builder {
    private String nestedFirstName;
    private String nestedLastName;
    private Long nestedgrossIncome;
    private Long nestedNetIncome;
    private Long nestedSuperAmount;
    private String nestedPaymentDate;
    private Long nestedIncomeTax;

    public Builder withFirstName(String nestedFirstName) {
      this.nestedFirstName = nestedFirstName;
      return this;
    }

    public Builder withLastName(String nestedLastName) {
      this.nestedLastName = nestedLastName;
      return this;
    }

    public Builder withgrossIncome(Long nestedgrossIncome) {
      this.nestedgrossIncome = nestedgrossIncome;
      return this;
    }

    public Builder withNetIncome(Long nestedNetIncome) {
      this.nestedNetIncome = nestedNetIncome;
      return this;
    }

    public Builder withSuperAmount(Long nestedSuperAmount) {
      this.nestedSuperAmount = nestedSuperAmount;
      return this;
    }

    public Builder withPaymentDate(String nestedPaymentDate) {
      this.nestedPaymentDate = nestedPaymentDate;
      return this;
    }

    public Builder withIncomeTax(Long nestedIncomeTax) {
      this.nestedIncomeTax = nestedIncomeTax;
      return this;
    }

    public PaySlipCsvBean build() {
      return new PaySlipCsvBean(nestedFirstName, nestedLastName, nestedPaymentDate, nestedgrossIncome, nestedIncomeTax, nestedNetIncome, nestedSuperAmount);
    }
  }
}