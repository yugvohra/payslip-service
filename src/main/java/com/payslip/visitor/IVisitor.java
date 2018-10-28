package com.payslip.visitor;

public interface IVisitor<T> {
  void visit(T visitable);
}
