package com.payslip.io.csv;

import com.payslip.io.csv.beans.EmployeeCSVBean;

public class InputValidator {

  public static boolean isValid(EmployeeCSVBean employeeCSVBean) {
    if (employeeCSVBean.getAnnualSalary() < 0 || employeeCSVBean.getSuperRate() < 0 || employeeCSVBean.getSuperRate() > 50)
      return false;
    return true;
  }

}
