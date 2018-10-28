package com.payslip.domain;

public class Employee {
  private final String firstName;
  private final String lastName;
  private final EmployeePay employeePay;

  public Employee(String firstName, String lastName, EmployeePay employeePay) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.employeePay = employeePay;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public EmployeePay getEmployeePay() {
    return employeePay;
  }
}
