package com.payslip.io.csv;

import com.payslip.io.csv.beans.EmployeeCSVBean;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputValidatorTest {

  @Test
  public void shouldReturnFalseForInvalidSalary() {
    //given
    EmployeeCSVBean employeeCsvBean = new EmployeeCSVBean("john", "dave", -1d, 10d, "2018-11-01");
    //then
    assertThat(InputValidator.isValid(employeeCsvBean)).isFalse();
  }

  @Test
  public void shouldReturnFalseForNegativeSuperRate() {
    //given
    EmployeeCSVBean employeeCsvBean = new EmployeeCSVBean("john", "dave", 1400d, -1d, "2018-11-01");
    //then
    assertThat(InputValidator.isValid(employeeCsvBean)).isFalse();
  }

  @Test
  public void shouldReturnFalseForInvalidSuperRate() {
    //given
    EmployeeCSVBean employeeCsvBean = new EmployeeCSVBean("john", "dave", 1400d, 51d, "2018-11-01");
    //then
    assertThat(InputValidator.isValid(employeeCsvBean)).isFalse();
  }

  @Test
  public void shouldReturnTrueForValidBean() {
    //given
    EmployeeCSVBean employeeCsvBean = new EmployeeCSVBean("john", "dave", 1400d, 10d, "2018-11-01");
    //then
    assertThat(InputValidator.isValid(employeeCsvBean)).isTrue();
  }

}
