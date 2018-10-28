package com.payslip.domain;

import java.util.Objects;

public class EmployeePay {
  private final Double annualSalary;
  private final Double grossIncome;
  private final Double superRate;
  private final Double superAmount;
  private final String paymentDate;

  public EmployeePay(Double annualSalary, Double grossIncome, Double superRate, Double superAmount, String paymentDate) {
    this.annualSalary = annualSalary;
    this.grossIncome = grossIncome;
    this.superRate = superRate;
    this.superAmount = superAmount;
    this.paymentDate = paymentDate;
  }

  public static EmployeePay from(Double aSalary, double superRate, String paymentDate) {
    Double grossIncome = aSalary / 12;
    Double superAmount = grossIncome * superRate / 100;
    return new EmployeePay(aSalary, grossIncome, superRate, superAmount, paymentDate);
  }

  public Double getAnnualSalary() {
    return annualSalary;
  }

  public Double getGrossIncome() {
    return grossIncome;
  }

  private Double getSuperRate() {
    return superRate;
  }

  public Double getSuperAmount() {
    return superAmount;
  }

  public String getPaymentDate() {
    return paymentDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmployeePay)) return false;
    EmployeePay that = (EmployeePay) o;
    return Objects.equals(getAnnualSalary(), that.getAnnualSalary()) &&
        Objects.equals(getGrossIncome(), that.getGrossIncome()) &&
        Objects.equals(getSuperRate(), that.getSuperRate()) &&
        Objects.equals(getSuperAmount(), that.getSuperAmount()) &&
        Objects.equals(getPaymentDate(), that.getPaymentDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAnnualSalary(), getGrossIncome(), getSuperRate(), getSuperAmount(), getPaymentDate());
  }
}
