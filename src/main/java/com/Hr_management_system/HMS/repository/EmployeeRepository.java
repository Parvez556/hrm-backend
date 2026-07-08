package com.Hr_management_system.HMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hr_management_system.HMS.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Your custom query methods (if any) will go here
}

