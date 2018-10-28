package com.payslip.io.csv.beans;

import com.opencsv.bean.CsvBindByName;

public class EmployeeCSVBean {
  @CsvBindByName(column = "first name")
  private String firstName;
  @CsvBindByName(column = "last name")
  private String lastName;
  @CsvBindByName(column = "annual salary")
  private Double annualSalary;
  @CsvBindByName(column = "super rate")
  private Double superRate;
  @CsvBindByName(column = "payment date")
  private String paymentDate;

  public EmployeeCSVBean() {

  }

  public EmployeeCSVBean(String firstName, String lastName, Double annualSalary, Double superRate, String paymentDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.annualSalary = annualSalary;
    this.superRate = superRate;
    this.paymentDate = paymentDate;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Double getAnnualSalary() {
    return annualSalary;
  }

  public Double getSuperRate() {
    return superRate;
  }

  public String getPaymentDate() {
    return paymentDate;
  }

}
