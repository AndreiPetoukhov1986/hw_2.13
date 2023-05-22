
package com.example.hw213.service;

import com.example.hw213.Employee;
import com.example.hw213.exception.EmployeeNotFoundException;
import com.example.hw213.exception.EmployeeAlreadyAddedExeption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String fullName,
                        int department,
                        double salary) {
        Employee employee = new Employee(fullName, department, salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedExeption();
        }
        employees.add(employee);
        return employee;
    }

    public Employee remove(String fullName) {
        Employee employee = employees.stream()
                .filter(e -> e.getFullName().equals(fullName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }

    public Employee find(String fullName) {
        return employees.stream()
                .filter(e -> e.getFullName().equals(fullName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
