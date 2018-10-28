package com.payslip.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.payslip.domain.Employee;
import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import com.payslip.io.csv.CsvIOExecutor;
import com.payslip.io.csv.beans.EmployeeCSVBean;
import com.payslip.io.csv.beans.PaySlipCsvBean;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CSVServiceTest {
  @Mock
  private CsvIOExecutor csvIOExecutor;
  private CSVService subject;
  private String filePath;

  @Before
  public void setUp() {
    subject = CSVService.getInstance();
    Whitebox.setInternalState(subject, "csvIOExecutor", csvIOExecutor);
    filePath = "sample.csv";
  }

  @Test
  public void shouldReturnEmployeeObject() throws IOException {
    //given
    List csvBeans = Lists.newArrayList(new EmployeeCSVBean("john", "dave", 100000d, 0.10d, "2018-04-01"));
    when(csvIOExecutor.fetchEmployeeBeans(anyString(), any())).thenReturn(csvBeans);
    Employee expectedEmployee = new Employee("john", "dave", EmployeePay.from(100000d, 0.10d, "2018-04-01"));
    //when
    List<Employee> employees = subject.fetchEmployeeFrom(filePath);
    //then
    assertThat(employees).hasSize(1);
    assertThat(employees.get(0)).isEqualToComparingFieldByField(expectedEmployee);
  }

  @Test
  public void shouldSkipInValidEmployee() throws IOException {
    //given
    List csvBeans = Lists.newArrayList(new EmployeeCSVBean("john", "dave", -1d, 0.10d, "2018-04-01"));
    when(csvIOExecutor.fetchEmployeeBeans(anyString(), any())).thenReturn(csvBeans);
    //when
    List<Employee> employees = subject.fetchEmployeeFrom(filePath);
    //then
    assertThat(employees).hasSize(0);
  }


  @Test
  public void shouldTransformBeansToCsv() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
    //given
    List<PaySlip> payslips = Lists.newArrayList(new PaySlip.PaySlipBuilder().withFirstName("john").withLastName("dave").withMonthDate("1 NOVEMBER - 30 NOVEMBER").withGrossIncome(10000L).
        withIncomeTax(3000L).withNetIncome(7000L).withSuperAmount(1000L).build());
    PaySlipCsvBean expectedCsvBean = new PaySlipCsvBean.Builder().withFirstName("john").withLastName("dave").withPaymentDate("1 NOVEMBER - 30 NOVEMBER").withgrossIncome(10000L).
        withIncomeTax(3000L).withNetIncome(7000L).withSuperAmount(1000L).build();

    ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
    doNothing().when(csvIOExecutor).writeBeanToCsv(any(), anyList());

    //when
    subject.writePaySlipsToCsv(payslips);

    //then
    verify(csvIOExecutor, times(1)).writeBeanToCsv(any(), argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().get(0)).isEqualToComparingFieldByField(expectedCsvBean);

  }

}
