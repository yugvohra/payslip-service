package com.payslip.main;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.payslip.facade.PaySlipWorkflowFacade;

import java.io.IOException;

public class Main {
  public static void main(String args[]) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
    PaySlipWorkflowFacade paySlipWorkflowFacade=new PaySlipWorkflowFacade();
    paySlipWorkflowFacade.execute("src/main/resources/EmployeePay.csv");
  }
}
