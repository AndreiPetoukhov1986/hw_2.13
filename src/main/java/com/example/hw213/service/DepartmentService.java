package com.example.hw213.service;

import com.example.hw213.Employee;
import com.example.hw213.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double maxSalaryFromDepartment(int dep) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public double minSalaryFromDepartment(int dep) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public double sumSalaryFromDepartment(int dep) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public List<Employee> printAllEmployeesForDep(int dep) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == dep)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> printAllEmployees() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
