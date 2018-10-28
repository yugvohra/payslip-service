package com.payslip.io.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * candidate for DI
 */
public class CsvIOExecutor {

  private static CsvIOExecutor INSTANCE = new CsvIOExecutor();

  private CsvIOExecutor() {
  }

  public static CsvIOExecutor getInstance() {
    return INSTANCE;
  }

  public List fetchEmployeeBeans(String filePath, Class clazz) throws IOException {
    FileReader reader = new FileReader(filePath);
    CsvToBean csvToBean = new CsvToBeanBuilder(reader).withType(clazz).build();
    List beans = csvToBean.parse();
    reader.close();
    return beans;
  }

  /*if in future the beans need to be wrote to a file */

  /* public void writeBeanToCsv(String filePath, Class clazz, List beans) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    FileWriter writer = new FileWriter(filePath);
    writeBeansToCsv(clazz, beans, writer);
    writer.close();
  }*/

  public void writeBeanToCsv(Class clazz, List beans) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    OutputStreamWriter writer = new OutputStreamWriter(System.out);
    writeBeansToCsv(clazz, beans, writer);
    writer.close();
  }

  private void writeBeansToCsv(Class clazz, List beans, OutputStreamWriter writer) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
    beanToCsv.write(beans);
  }
}
