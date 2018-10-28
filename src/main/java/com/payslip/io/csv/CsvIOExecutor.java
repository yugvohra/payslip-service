package com.payslip.io.csv;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.List;

public class CsvIOExecutor {

  public List fetchEmployeeBeans(String filePath, Class clazz) throws IOException {
    FileReader reader = new FileReader(filePath);
    CsvToBean csvToBean = new CsvToBeanBuilder(reader).withType(clazz).build();
    List beans = csvToBean.parse();
    reader.close();
    return beans;
  }

  public void writeBeanToCsv(String filePath, Class clazz, List beans) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    FileWriter writer = new FileWriter(filePath);
    writeBeansToCsv(clazz, beans, writer);
    writer.close();
  }

  public void writeBeanToCsv( Class clazz, List beans) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    OutputStreamWriter writer = new OutputStreamWriter(System.out);
    writeBeansToCsv(clazz, beans, writer);
    writer.close();
  }

  private void writeBeansToCsv(Class clazz, List beans, OutputStreamWriter writer) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
    beanToCsv.write(beans);
  }
}
