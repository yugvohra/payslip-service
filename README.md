# payslip-service

# This project generates the payslip of an employee. Employee details are read from the csv file (present in src/main/resourcces).

# Design
for design decissions , have a look at design.md in the root directory

# Assumptions
Detailed Assumptions could be found in Assumptions.md file in root directory

# To run the programm follow the below steps

1) Enter the details in the EmployeeData.csv (src/main/resources)

The input format is
(first name,last name,annual salary , super rate, payment start date)

2) Execute the main programm present in the main package

3) The resultant payslips are printed on the console
(This behaviour could be changed by enabling the FileWriter in CsVIOExecutor.java)

# Quality analysis
1)Test cases cover 98% of Code . Main method and hashcode methods are outside the purview of unit testing
2) Main program generates the exact payslips as mentioned in the problem

