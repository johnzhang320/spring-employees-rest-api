package com.spring.employees.rest.api.controller;

import com.spring.employees.rest.api.model.Employees;
import com.spring.employees.rest.api.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
//(origins = "http://localhost:4200")  //make angular can access this controller by allow cross origin way

@CrossOrigin(allowedHeaders = {"Authorization", "Origin"})
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeeRepository employeeRepository;
    @GetMapping()
    public List<Employees> getEmployeesList() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employees getEmployeesList(@PathVariable("id") Integer id) {
        return employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Can not find employee !"));
    }

    @PostMapping()
    public Employees createEmployee(@RequestBody Employees employees) {
        return employeeRepository.save(employees);
    }

    @PutMapping()
    public Employees updateEmployees(@RequestBody Employees employees ) {

        if (employees.getId()==null)  {
            throw new RuntimeException("Can not find employee , id is required");
        }
        employeeRepository.findById(employees.getId()).orElseThrow(()->new RuntimeException("Can not find employee !"));

        return employeeRepository.save(employees);

    }

    @DeleteMapping()
    public List<Employees> deleteEmployees(@RequestBody Employees employees ) {
        if (employees.getId()==null)  {
            throw new RuntimeException("Can not find employee , id is required");
        }
        employeeRepository.findById(employees.getId()).orElseThrow(()->new RuntimeException("Can not find employee !"));
        employeeRepository.deleteById(employees.getId());
        return employeeRepository.findAll();
    }
}
