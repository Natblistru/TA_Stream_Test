package service;

import model.Employee;
import model.Gender;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    // Adăugați într-o listă 3 femei și 3 bărbați, alegeți aleator datele
    public static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            double randomSalary = Math.round((1000 + Math.random() * 9000) * 100.0) / 100.0; // generare cu 2 zecimale
            employees.add(new Employee("Person" + i, randomSalary, Gender.FEMALE, LocalDate.now().minusYears(random.nextInt(31) + 20)));

            randomSalary = Math.round((1000 + Math.random() * 9000) * 100.0) / 100.0;
            employees.add(new Employee("Person" + (i + 3), randomSalary, Gender.MALE, LocalDate.now().minusYears(random.nextInt(31) + 20)));
        }

        return employees;
    }

    // 1) 3 persoane care au salariul cel mai mare salariu din lista
    // Am divizat in 2 metode (obtinere și afișare) pentru a putea testa metoda de obtinere
    public static void printEmployeesWithHighestSalary(List<Employee> employees) {
        List<Employee> top3Employees = getTop3EmployeesBySalary(employees);

        System.out.println("Top 3 employees with highest salary:");
        top3Employees.forEach(EmployeeService::printEmployee);
        System.out.println();
    }

    public static List<Employee> getTop3EmployeesBySalary(List<Employee> employees) {
        return employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public static void printEmployeesWithHighestSalaryNoStream(List<Employee> employees) {
        bubbleSortBySalaryDesc(employees);
        for (int i = 0; i < 3 && i < employees.size(); i++) {
            printEmployee(employees.get(i));
        }
        System.out.println();
    }

    private static void bubbleSortBySalaryDesc(List<Employee> employees) {
        int n = employees.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (employees.get(j).getSalary() < employees.get(j + 1).getSalary()) {
                    // Swap
                    Employee temp = employees.get(j);
                    employees.set(j, employees.get(j + 1));
                    employees.set(j + 1, temp);
                }
            }
        }
    }

    // 2. 2 femei care au birthdate după 01.01.1990
    // Am divizat in 2 metode (obtinere și afișare) pentru a putea testa metoda de obtinere
    public static void printFemalesBornAfter1990(List<Employee> employees) {
        List<Employee> femalesBornAfter1990 = getFemalesBornAfter1990(employees);
        System.out.println("Females born after 01-01-1990:");
        femalesBornAfter1990.forEach(EmployeeService::printEmployee);
        System.out.println();
    }

    public static List<Employee> getFemalesBornAfter1990(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getGender() == Gender.FEMALE && e.getBirthdate().isAfter(LocalDate.of(1990, 1, 1)))
                .collect(Collectors.toList());
    }

    public static void printFemalesBornAfter1990NoStream(List<Employee> employees) {
        int count = 0;
        for (Employee e : employees) {
            if (e.getGender() == Gender.FEMALE && e.getBirthdate().isAfter(LocalDate.of(1990, 1, 1))) {
                printEmployee(e);
                count++;
                if (count == 2) {
                    break;
                }
            }
        }
        System.out.println();
    }

    // 3. Informatia despre bărbații din listă
    public static void printMalesInfo(List<Employee> employees) {
        List<Employee> males = getMales(employees);
        System.out.println("Male Information:");
        males.forEach(EmployeeService::printEmployee);
        System.out.println();
    }
    public static List<Employee> getMales(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getGender() == Gender.MALE)
                .collect(Collectors.toList());
    }

    public static void printMaleInfoNoStream(List<Employee> employees) {
        for (Employee e : employees) {
            if (e.getGender() == Gender.MALE) {
                printEmployee(e);
            }
        }
        System.out.println();
    }

    // 4. Doar salariile tuturor persoanelor ordonate crescător
    public static void printSortedSalaries(List<Employee> employees) {
        List<Double> sortedSalaries = getSortedSalaries(employees);
        System.out.println("Sorted Salaries:");
        sortedSalaries.forEach(System.out::println);
        System.out.println();
    }
    public static List<Double> getSortedSalaries(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getSalary)
                .sorted()
                .collect(Collectors.toList());
    }

    public static void printSortedSalariesNoStream(List<Employee> employees) {
        List<Double> salaries = new ArrayList<>();
        for (Employee employee : employees) {
            salaries.add(employee.getSalary());
        }

        Collections.sort(salaries);

        for (Double salary : salaries) {
            System.out.println(salary);
        }
        System.out.println();
    }

    // 5. Doar salariile unice ale persoanelor
    public static void printUniqueSalaries(List<Employee> employees) {
        List<Double> uniqueSalaries = getUniqueSalaries(employees);
        System.out.println("Unique Salaries:");
        uniqueSalaries.forEach(System.out::println);
        System.out.println();
    }
    public static List<Double> getUniqueSalaries(List<Employee> employees) {
        Map<Double, Long> salaryCountMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()));

        return salaryCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void printUniqueSalariesNoStream(List<Employee> employees) {
        Map<Double, Integer> salaryCountMap = new HashMap<>();

        // Numărăm de câte ori apare fiecare salariu
        for (Employee employee : employees) {
            double salary = employee.getSalary();
            salaryCountMap.put(salary, salaryCountMap.getOrDefault(salary, 0) + 1);
        }

        // Construim lista cu salariile unice
        List<Double> uniqueSalaries = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : salaryCountMap.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueSalaries.add(entry.getKey());
            }
        }
        for (Double salary : uniqueSalaries) {
            System.out.println(salary);
        }
        System.out.println();
    }


    private static void printEmployee(Employee employee) {
        System.out.println("Name: " + employee.getName() + ", Salary: " + employee.getSalary() + ", Gender: " + employee.getGender() + ", Birthdate: " + employee.getBirthdate());
    }
}
