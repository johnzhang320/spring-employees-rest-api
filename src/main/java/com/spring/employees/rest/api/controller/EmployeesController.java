package com.spring.employees.rest.api.controller;

import com.spring.employees.rest.api.model.Employees;
import com.spring.employees.rest.api.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  in Tomcat filter, adding
 *  <filter>
 *   <filter-name>CorsFilter</filter-name>
 *   <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
 * </filter>
 * <filter-mapping>
 *   <filter-name>CorsFilter</filter-name>
 *   <url-pattern>/*</url-pattern>
 * </filter-mapping>
 */
@RestController

//@RequestMapping("/spring-employees-rest-api")
@Slf4j
//@RequestMapping("/api/v1/employees")
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
        log.info("Employee POST Submitted:"+employees.toString());
        return employeeRepository.save(employees);
    }

    @PutMapping("/{id}")
    public Employees updateEmployees(@RequestBody Employees employees,@PathVariable Integer id ) {
        log.info("Employee PUT Submitted:"+employees.toString());
        if (id==null)  {
            throw new RuntimeException("id is required");
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
