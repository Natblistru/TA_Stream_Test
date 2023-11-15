import model.Employee;
import service.EmployeeService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeService.generateEmployees();

        EmployeeService.printEmployeesWithHighestSalary(employees);
        EmployeeService.printFemalesBornAfter1990(employees);
        EmployeeService.printMalesInfo(employees);
        EmployeeService.printSortedSalaries(employees);
        EmployeeService.printUniqueSalaries(employees);
    }
}
