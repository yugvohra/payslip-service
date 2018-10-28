package com.payslip.domain;

import com.payslip.visitor.IVisitor;

public final class PaySlip {
  private String firstName;
  private String lastName;
  private String paymentDate;
  private Long grossIncome;
  private Long incomeTax;
  private Long netIncome;
  private Long superAmount;

  private PaySlip(String firstName, String lastName, String paymentDate, Long grossIncome, Long incomeTax, Long netIncome, Long superAmount) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.paymentDate = paymentDate;
    this.grossIncome = grossIncome;
    this.incomeTax = incomeTax;
    this.netIncome = netIncome;
    this.superAmount = superAmount;
  }

  public void accept(IVisitor<PaySlip> visitor) {
    visitor.visit(this);
  }

  public Long getGrossIncome() {
    return grossIncome;
  }

  public void setGrossIncome(Long grossIncome) {
    this.grossIncome = grossIncome;
  }

  public Long getIncomeTax() {
    return incomeTax;
  }

  public void setIncomeTax(Long incomeTax) {
    this.incomeTax = incomeTax;
  }

  public Long getNetIncome() {
    return netIncome;
  }

  public void setNetIncome(Long netIncome) {
    this.netIncome = netIncome;
  }

  public Long getSuperAmount() {
    return superAmount;
  }

  public void setSuperAmount(Long superAmount) {
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

  public void setPaymentDate(String paymentDate) {
    this.paymentDate = paymentDate;
  }

  public static class PaySlipBuilder {

    private String nestedfirstName;
    private String nestedlastName;
    private Long nestedgrossIncome;
    private Long nestedincomeTax;
    private String nestedmonthDate;
    private Long nestednetIncome;
    private Long nestedsuperAmount;

    public PaySlipBuilder withFirstName(String firstName) {
      this.nestedfirstName = firstName;
      return this;
    }

    public PaySlipBuilder withLastName(String lastName) {
      this.nestedlastName = lastName;
      return this;
    }

    public PaySlipBuilder withMonthDate(String monthDate) {
      this.nestedmonthDate = monthDate;
      return this;
    }

    public PaySlipBuilder withGrossIncome(Long grossIncome) {
      this.nestedgrossIncome = grossIncome;
      return this;
    }

    public PaySlipBuilder withIncomeTax(Long incomeTax) {
      this.nestedincomeTax = incomeTax;
      return this;
    }

    public PaySlipBuilder withNetIncome(Long netIncome) {
      this.nestednetIncome = netIncome;
      return this;
    }

    public PaySlipBuilder withSuperAmount(Long superAmount) {
      this.nestedsuperAmount = superAmount;
      return this;
    }

    public PaySlip build() {
      return new PaySlip(nestedfirstName, nestedlastName, nestedmonthDate, nestedgrossIncome, nestedincomeTax, nestednetIncome, nestedsuperAmount);
    }
  }
}
