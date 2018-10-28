package com.payslip.facade;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.payslip.domain.Employee;
import com.payslip.domain.PaySlip;
import com.payslip.service.CSVService;
import com.payslip.service.PaySlipService;
import com.payslip.service.TaxService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rudimentary facade
 */
public class PaySlipWorkflowFacade {
  private PaySlipService paySlipService;
  private TaxService taxService;
  private CSVService csvService;

  public PaySlipWorkflowFacade() {
    initialize();
  }

  private void initialize() {
    this.paySlipService = PaySlipService.getInstance();
    this.taxService = TaxService.getInstance();
    this.csvService = CSVService.getInstance();
  }

  public void execute(String inputFilePath) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    writeToCsv(generatePayslipsFor(fetchEmployeeFromCsv(inputFilePath)));
  }

  private List<Employee> fetchEmployeeFromCsv(String path) throws IOException {
    return csvService.fetchEmployeeFrom(path);
  }

  private List<PaySlip> generatePayslipsFor(List<Employee> employees) {
    return employees.stream().map(this::generatePayslip).collect(Collectors.toList());
  }

  private void writeToCsv(List<PaySlip> paySlips) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
    csvService.writePaySlipsToCsv(paySlips);
  }

  private PaySlip generatePayslip(Employee employee) {
    Long computedTax = taxService.getRoundedOffTaxForMonth(employee.getEmployeePay().getAnnualSalary());
    return paySlipService.generatePayslip(employee, computedTax);
  }

}
