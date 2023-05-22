package com.example.hw213.controller;

import com.example.hw213.Employee;
import com.example.hw213.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> printAllEmployeesForDep(@PathVariable int id){
        return departmentService.printAllEmployeesForDep(id);
    }

    @GetMapping("{id}/salary/sum")
    public double sumSalaryFromDepartment(@PathVariable int id){
        return departmentService.sumSalaryFromDepartment(id);
    }

    @GetMapping("{id}/salary/max")
    public double maxSalaryFromDepartment(@PathVariable int id){
        return departmentService.maxSalaryFromDepartment(id);
    }

    @GetMapping("{id}/salary/min")
    public double minSalaryFromDepartment(@PathVariable int id){
        return departmentService.minSalaryFromDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> printAllEmployees(){
        return departmentService.printAllEmployees();
    }

}

