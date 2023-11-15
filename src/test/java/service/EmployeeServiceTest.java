package service;

import model.Employee;
import model.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Test
    void getTop3EmployeesBySalary_shouldReturnCorrectEmployees() {
        Employee employee1 = new Employee("Person1", 5000, Gender.FEMALE, LocalDate.now().minusYears(25));
        Employee employee2 = new Employee("Person2", 7000, Gender.FEMALE, LocalDate.now().minusYears(30));
        Employee employee3 = new Employee("Person3", 6000, Gender.FEMALE, LocalDate.now().minusYears(28));
        Employee employee4 = new Employee("Person4", 8000, Gender.MALE, LocalDate.now().minusYears(32));
        Employee employee5 = new Employee("Person5", 7500, Gender.MALE, LocalDate.now().minusYears(29));
        Employee employee6 = new Employee("Person6", 9000, Gender.MALE, LocalDate.now().minusYears(35));

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6);

        List<Employee> top3Employees = EmployeeService.getTop3EmployeesBySalary(employees);

        // Creăm lista așteptată
        List<Employee> expectedTop3Employees = Arrays.asList(employee6, employee4, employee5);

        assertArrayEquals(expectedTop3Employees.toArray(), top3Employees.toArray());
    }

    @Test
    void getFemalesBornAfter1990_shouldReturnCorrectEmployees() {
        Employee female1 = new Employee("Female1", 5000, Gender.FEMALE, LocalDate.of(1992, 5, 20));
        Employee female2 = new Employee("Female2", 7000, Gender.FEMALE, LocalDate.of(1988, 8, 15));
        Employee male1 = new Employee("Male1", 6000, Gender.MALE, LocalDate.of(1995, 3, 10));
        Employee male2 = new Employee("Male2", 8000, Gender.MALE, LocalDate.of(1985, 12, 5));
        Employee female3 = new Employee("Female3", 5500, Gender.FEMALE, LocalDate.of(1990, 7, 12));
        Employee female4 = new Employee("Female4", 7200, Gender.FEMALE, LocalDate.of(1991, 9, 18));

        List<Employee> employees = Arrays.asList(female1, female2, male1, male2, female3, female4);

        List<Employee> femalesBornAfter1990 = EmployeeService.getFemalesBornAfter1990(employees);

        // Creăm lista așteptată
        List<Employee> expectedFemalesBornAfter1990 = Arrays.asList(female1, female3, female4);

        assertEquals(expectedFemalesBornAfter1990, femalesBornAfter1990);
    }

    @Test
    void getMales_shouldReturnCorrectEmployees() {
        // Creare 6 angajați cu diferite genuri
        Employee male1 = new Employee("Male1", 6000, Gender.MALE, LocalDate.of(1995, 3, 10));
        Employee male2 = new Employee("Male2", 8000, Gender.MALE, LocalDate.of(1985, 12, 5));
        Employee female1 = new Employee("Female1", 5000, Gender.FEMALE, LocalDate.of(1992, 5, 20));
        Employee female2 = new Employee("Female2", 7000, Gender.FEMALE, LocalDate.of(1988, 8, 15));
        Employee female3 = new Employee("Female3", 5500, Gender.FEMALE, LocalDate.of(1990, 7, 12));
        Employee female4 = new Employee("Female4", 7200, Gender.FEMALE, LocalDate.of(1991, 9, 18));

        List<Employee> employees = Arrays.asList(male1, male2, female1, female2, female3, female4);

        List<Employee> males = EmployeeService.getMales(employees);

        List<Employee> expectedMales = Arrays.asList(male1, male2);

        assertEquals(expectedMales, males);
    }

    @Test
    void getSortedSalaries_shouldReturnCorrectSalaries() {
        // Creare 4 angajați cu salarii diferite
        Employee employee1 = new Employee("Person1", 5000, Gender.FEMALE, LocalDate.now().minusYears(25));
        Employee employee2 = new Employee("Person2", 7000, Gender.FEMALE, LocalDate.now().minusYears(30));
        Employee employee3 = new Employee("Person3", 6000, Gender.MALE, LocalDate.now().minusYears(28));
        Employee employee4 = new Employee("Person4", 8000, Gender.MALE, LocalDate.now().minusYears(32));

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4);

        List<Double> sortedSalaries = EmployeeService.getSortedSalaries(employees);

        List<Double> expectedSortedSalaries = Arrays.asList(5000.0, 6000.0, 7000.0, 8000.0);

        // Comparaă listele tinând cont de ordinea elementelor
        assertIterableEquals(expectedSortedSalaries, sortedSalaries);
    }

    @Test
    void getUniqueSalaries_shouldReturnCorrectSalaries() {
        // Creare 6 angajați cu salarii diferite
        Employee employee1 = new Employee("Person1", 5000, Gender.FEMALE, LocalDate.now().minusYears(25));
        Employee employee2 = new Employee("Person2", 7000, Gender.FEMALE, LocalDate.now().minusYears(30));
        Employee employee3 = new Employee("Person3", 6000, Gender.MALE, LocalDate.now().minusYears(28));
        Employee employee4 = new Employee("Person4", 8000, Gender.MALE, LocalDate.now().minusYears(32));
        Employee employee5 = new Employee("Person5", 7000, Gender.FEMALE, LocalDate.now().minusYears(27));
        Employee employee6 = new Employee("Person6", 6000, Gender.MALE, LocalDate.now().minusYears(31));

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6);

        List<Double> uniqueSalaries = EmployeeService.getUniqueSalaries(employees);

        List<Double> expectedUniqueSalaries = Arrays.asList(5000.0, 8000.0);

        assertEquals(expectedUniqueSalaries, uniqueSalaries);
    }
}