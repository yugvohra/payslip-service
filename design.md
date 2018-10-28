[200~Design Decisions 
# Payslip Generators uses ChainOfCommand pattern at its heart to calculate tax on the salary. 
This was preferred over strategy pattern as income tax slabs are prone to change
i.e new slabs could be added or old could be modified in subsequent financial years
# Tax calculation 
uses chain of command design pattern to calculate income tax
Command could be of three types : Nil (which does not accumulate tax) , intermediate slab
(for different income slabs) , max(for last slab of income tax)

# CsvIO
OpenCsv is used to read and write CSV.As of now Solution writes to the console but that could be extended

# PoJO objects
Builder pattern is used to Build heavy domain objects like payslip or Employee

# PayLoad factor
If employee started working for a date other than 1st of month ,then his/her salary is factor of salary
Visitor pattern is used to modify the payslip in such cases 
