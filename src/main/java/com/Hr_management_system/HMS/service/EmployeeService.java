package com.Hr_management_system.HMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.Hr_management_system.HMS.model.Employee;
import com.Hr_management_system.HMS.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // add employee method
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    // get all employee list method
    public List<Employee> getAllEmployees() { // Return type List<Employee> hona chahiye
        return employeeRepository.findAll();
    }

    // get employee by id
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    // update employee method
    public Employee updateEmployee(int id, Employee employeeDetails) {
        // 1. Check if the employee exists in the database
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        // 2. Update the existing employee's details with new data
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setDesignation(employeeDetails.getDesignation());
        existingEmployee.setSalary(employeeDetails.getSalary());

        // 3. Save the updated entity back to the database
        return employeeRepository.save(existingEmployee);
    }

    // delete method by id
    public String deleteEmployeeById(int id) {
         
      if (!employeeRepository.existsById(id)) {
        return "Employee with id " + id + " does not exist!";
    }
    
    employeeRepository.deleteById(id);
    return "Data has been deleted successfully for id: " + id;
    }

}
