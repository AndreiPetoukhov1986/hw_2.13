package com.example.hw213.service;

import com.example.hw213.Employee;
import com.example.hw213.exception.EmployeeAlreadyAddedExeption;
import com.example.hw213.exception.EmployeeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    public void beforeEach() {
        employeeService.add("Петухов Андрей", 1, 100_000);
        employeeService.add("Марьенко Людмила", 2, 95_000);
        employeeService.add("Ожиганова Наталья", 3, 90_000);
    }

    @AfterEach
    public void afterEach() {
        employeeService.getAll()
                .forEach((employee -> employeeService.remove(employee.getFullName())));
    }

    @Test
    public void addTest() {
        Employee expected = new Employee("Петров Петя", 1, 97_000);

        Assertions.assertThat(employeeService.add("Петров Петя", 1, 97_000))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        Assertions.assertThat(employeeService.find("Петров Петя").equals(expected));
    }

    @Test
    public void addExistTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedExeption.class)
                .isThrownBy(() -> employeeService.add("Петухов Андрей", 1, 100_000));
    }

    @Test
    public void removeTest() {
        Employee expected = new Employee("Петухов Андрей", 1, 100_000);

        Assertions.assertThat(employeeService.remove("Петухов Андрей"))
                .isEqualTo(expected)
                .isNotIn(employeeService.getAll());
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Петухов Андрей"));
    }

    @Test
    public void removeNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Аверина Галина"));
    }

    @Test
    public void findTest() {
        Employee expected = new Employee("Петухов Андрей", 1, 100_000);

        Assertions.assertThat(employeeService.find("Петухов Андрей"))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        ;
    }

    @Test
    public void findNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Аверина Галина"));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(employeeService.getAll())
                .containsExactlyInAnyOrder(
                        new Employee("Петухов Андрей", 1, 100_000),
                        new Employee("Марьенко Людмила", 2, 95_000),
                        new Employee("Ожиганова Наталья", 3, 90_000)
                        );
    }
}
