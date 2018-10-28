package com.payslip.io.csv;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvIOExecutorTest {
  private String testFilePath = "src/main/resources/EmployeePay.csv";
  private CsvIOExecutor csvIOExecutor;

  @Before
  public void setUp() {
    csvIOExecutor = new CsvIOExecutor();
  }

  @Test
  public void shouldBeAbleToReadEmployeePay() throws IOException {
    //given
    EmployeeCSVBean expectedCsvBean = new EmployeeCSVBean("john", "dave", 140000d, 10d, "November");
    //when
    List beans = csvIOExecutor.fetchEmployeeBeans(testFilePath, EmployeeCSVBean.class);
    //then
      assertThat(beans.get(0)).isEqualToComparingFieldByField(expectedCsvBean);

  }
}

