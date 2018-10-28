package com.payslip.domain;

public final class PaySlip {
  private final String firstName;
  private final String lastName;
  private final String monthDate;
  private final Long grossIncome;
  private final Long incomeTax;
  private final Long netIncome;
  private final Long superAmount;

  private PaySlip(String firstName, String lastName, String monthDate, Long grossIncome, Long incomeTax, Long netIncome, Long superAmount) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.monthDate = monthDate;
    this.grossIncome = grossIncome;
    this.incomeTax = incomeTax;
    this.netIncome = netIncome;
    this.superAmount = superAmount;
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

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMonthDate() {
    return monthDate;
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
