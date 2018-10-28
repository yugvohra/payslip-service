package com.payslip.visitor.impl;

import com.payslip.domain.PaySlip;
import com.payslip.domain.PaySlipConstants;
import com.payslip.visitor.IVisitor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.round;

public class PaySLipVisitor implements IVisitor<PaySlip> {

  @Override
  public void visit(PaySlip visitable) {
    LocalDate paymentStartDate = getPaymentStartDate(visitable.getPaymentDate());
    double paidFactor = getPayFactor(paymentStartDate);

    visitable.setGrossIncome(getFactoredAmount(visitable.getGrossIncome(), paidFactor));
    visitable.setIncomeTax(getFactoredAmount(visitable.getIncomeTax(), paidFactor));
    visitable.setNetIncome(getFactoredAmount(visitable.getNetIncome(), paidFactor));
    visitable.setSuperAmount(getFactoredAmount(visitable.getSuperAmount(), paidFactor));

    visitable.setPaymentDate(getPayPeriod(paymentStartDate));
  }

  private long getFactoredAmount(Long amount, double paidFactor) {
    return round(amount * paidFactor);
  }


  private double getPayFactor(LocalDate paymentStartDate) {
    int numberOfDaysInMonth = paymentStartDate.lengthOfMonth();
    int numberOfDaysWorked = numberOfDaysInMonth - paymentStartDate.getDayOfMonth() + 1;
    return ((double) numberOfDaysWorked) / numberOfDaysInMonth;
  }

  private LocalDate getPaymentStartDate(String paymentStartDate) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PaySlipConstants.PAY_START_DATE_FORMAT);
    return LocalDate.parse(paymentStartDate, dateTimeFormatter);
  }

  private String getPayPeriod(LocalDate date) {
    return String.format("%s %s - %s %s", date.getDayOfMonth(), date.getMonth(), date.lengthOfMonth(), date.getMonth());
  }
}
