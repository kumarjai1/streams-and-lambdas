package com.ga.streams;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
        list.forEach(str -> System.out.println(str));
    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employees = this.employees.stream()
                .filter(over50k -> over50k.getSalary() > 50_000)
                .collect(Collectors.toList());
        printList(employees);
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<String> employees = this.employees.stream()
                .filter(after2012 -> after2012.getHireDate().getYear() > 2012)
                .map(employeeName -> employeeName.getName())
                .collect(Collectors.toList());
        printList(employees);
    }

    @Test
    public void getMaxSalary() {
        double max = this.employees.stream()
                .max(Comparator.comparing(maxSal -> maxSal.getSalary()))
                .map(maxSal -> maxSal.getSalary())
                .orElse(null);
        System.out.println("Max:" + max);
    }

    @Test
    public void getMinSalary() {
        double min = this.employees.stream()
                .min(Comparator.comparing(minSal -> minSal.getSalary()))
                .map(minSal -> minSal.getSalary())
                .orElse(null);
        System.out.println("Min:" + min);
    }

    @Test
    public void getAverageSalaries() {
        double averageMale = this.employees.stream()
                .filter(employee -> employee.getGender() == "Male")
                .mapToDouble(employee -> employee.getSalary())
                .average()
                .orElse(0);
        double averageFemale = this.employees.stream()
                .filter(employee -> employee.getGender() == "Female")
                .mapToDouble(employee -> employee.getSalary())
                .average()
                .orElse(0);

        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
//        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = this.employees.stream()
                .max(Comparator.comparing(employee -> employee.getSalary()))
                .orElse(null);
        System.out.println(highest);

        Employee highest2 = this.employees.stream()
                .reduce((employee1, employee2) -> employee1.getSalary() > employee2.getSalary() ? employee1 : employee2)
                .orElse(null);

        System.out.println("Using reduce: " + highest2);
    }
}

