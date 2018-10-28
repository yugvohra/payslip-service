package com.payslip.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.payslip.domain.Employee;
import com.payslip.domain.EmployeePay;
import com.payslip.domain.PaySlip;
import com.payslip.io.csv.CsvIOExecutor;
import com.payslip.io.csv.EmployeeCSVBean;
import com.payslip.io.csv.PaySlipCsvBean;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 * Move this to strategy pattern for other I/O stream
 */
public class CSVService {
  private final CsvIOExecutor csvIOExecutor;

  public CSVService() {
    this.csvIOExecutor = new CsvIOExecutor();
  }

  public List<Employee> fetchEmployeeFrom(String filePath) throws IOException {
    return (List<Employee>) csvIOExecutor.fetchEmployeeBeans(filePath, EmployeeCSVBean.class).stream().map(bean -> from((EmployeeCSVBean) bean)).collect(Collectors.toList());

  }

  public void writePaySlipsToCsv(List<PaySlip> payslips) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
    List csvBeans=payslips.parallelStream().map(this::from).collect(Collectors.toList());
    csvIOExecutor.writeBeanToCsv(PaySlipCsvBean.class,csvBeans);
  }

  private Employee from(EmployeeCSVBean aCsvBean) {
    return new Employee(aCsvBean.getFirstName(), aCsvBean.getLastName(), EmployeePay.from(aCsvBean.getAnnualSalary(), aCsvBean.getSuperRate(), aCsvBean.getPaymentDate()));
  }

  private PaySlipCsvBean from(PaySlip aPayslip) {
    return new PaySlipCsvBean.Builder().withFirstName(aPayslip.getFirstName()).withLastName(aPayslip.getLastName()).withgrossIncome(aPayslip.getGrossIncome()).
        withIncomeTax(aPayslip.getIncomeTax()).withNetIncome(aPayslip.getNetIncome()).withPaymentDate(aPayslip.getMonthDate()).withSuperAmount(aPayslip.getSuperAmount()).build();
  }
}
