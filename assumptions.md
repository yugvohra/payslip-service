#Assumptions 
1) Lombok plugin is not used. Hence, getters, setters and builders are written from scratch

2) Spring MVC is not used and DI pattern is implemented using inner static factories

3) Csv file is used to read the date of employees

4) Payment startDate is fed in form of yyyy-mm-dd.
This helps in calculating period for leap year and also sorting the dates
(maybe needed in future)

5) For payment startdate other than first of month , the calculations are factored by
   numberOfdaysWork/totaldaysofmonth 

5) Input is validated only on two parameters superRate and annual salary

6)Amount is rounded off before the calculation of net salary(as follows)

rounded_gross_income- rounded_income_tax_per_month
