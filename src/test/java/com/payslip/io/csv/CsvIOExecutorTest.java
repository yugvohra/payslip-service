package com.payslip.io.csv;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.payslip.io.csv.beans.EmployeeCSVBean;
import com.payslip.io.csv.beans.PaySlipCsvBean;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CsvIOExecutorTest {
  @Mock
  PrintStream printStream;
  private CsvIOExecutor csvIOExecutor;

  @Before
  public void setUp() {
    csvIOExecutor = CsvIOExecutor.getInstance();
    System.setOut(printStream);
  }

  @Test
  public void shouldBeAbleToReadEmployeePay() throws IOException {
    //given
    EmployeeCSVBean expectedCsvBean = new EmployeeCSVBean("john", "dave", 140000d, 10d, "2018-11-01");
    //when
    String testFilePath = "src/test/resources/EmployeePay.csv";
    List beans = csvIOExecutor.fetchEmployeeBeans(testFilePath, EmployeeCSVBean.class);
    //then
    assertThat(beans.get(0)).isEqualToComparingFieldByField(expectedCsvBean);

  }

  @Test
  public void shouldBeAbleToWriteBeansToCsv() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
    //given
    PaySlipCsvBean csvBean = new PaySlipCsvBean.Builder().withFirstName("john").withLastName("dave").withPaymentDate("1 NOVEMBER - 30 NOVEMBER").withgrossIncome(10000L).
        withIncomeTax(3000L).withNetIncome(7000L).withSuperAmount(1000L).build();
    List<PaySlipCsvBean> beans = Lists.newArrayList(csvBean);

    //when
    csvIOExecutor.writeBeanToCsv(PaySlipCsvBean.class, beans);

    //then
    verify(printStream, times(1)).write(any(byte[].class), anyInt(), anyInt());


  }
}

