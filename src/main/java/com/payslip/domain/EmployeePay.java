package com.payslip.domain;

public class EmployeePay {
  private final Double annualSalary;
  private final Double grossIncome;
  private final Double superRate;
  private final Double superAmount;

  public EmployeePay(Double annualSalary, Double grossIncome, Double superRate, Double superAmount) {
    this.annualSalary = annualSalary;
    this.grossIncome = grossIncome;
    this.superRate = superRate;
    this.superAmount = superAmount;
  }

  public static EmployeePay from(Double aSalary, double superRate) {
    Double grossIncome = aSalary / 12;
    Double superAmount = grossIncome * superRate;
    return new EmployeePay(aSalary, grossIncome, superRate, superAmount);
  }

  public Double getAnnualSalary() {
    return annualSalary;
  }

  public Double getGrossIncome() {
    return grossIncome;
  }

  public Double getSuperRate() {
    return superRate;
  }

  public Double getSuperAmount() {
    return superAmount;
  }

}
