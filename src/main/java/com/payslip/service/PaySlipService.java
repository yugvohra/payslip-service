package com.payslip.service;

import com.payslip.domain.Employee;
import com.payslip.domain.PaySlip;
import com.payslip.visitor.IVisitor;
import com.payslip.visitor.impl.PaySLipVisitor;

import static java.lang.Math.round;

public class PaySlipService {

  private static PaySlipService INSTANCE = new PaySlipService();

  private PaySlipService() {
  }

  public static PaySlipService getInstance() {
    return INSTANCE;
  }

  public PaySlip generatePayslip(Employee employee, Long computedTax) {
    Long netIncome = round(employee.getEmployeePay().getGrossIncome()) - computedTax;
    PaySlip payslip = new PaySlip.PaySlipBuilder().withFirstName(employee.getFirstName()).withLastName(employee.getLastName()).withMonthDate(employee.getEmployeePay().getPaymentDate()).withGrossIncome(round(employee.getEmployeePay().getGrossIncome())).
        withIncomeTax(computedTax).withNetIncome(netIncome).withSuperAmount(round(employee.getEmployeePay().getSuperAmount())).build();
    IVisitor<PaySlip> paySlipIVisitor = new PaySLipVisitor();
    payslip.accept(paySlipIVisitor);
    return payslip;
  }

}
