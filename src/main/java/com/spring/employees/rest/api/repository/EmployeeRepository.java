package com.spring.employees.rest.api.repository;

import com.spring.employees.rest.api.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

}
