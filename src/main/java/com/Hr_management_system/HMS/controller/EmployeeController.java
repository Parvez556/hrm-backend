package com.Hr_management_system.HMS.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hr_management_system.HMS.model.Employee;
import com.Hr_management_system.HMS.service.EmployeeService;





@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "http://localhost:5173") // Allow your React app
public class EmployeeController {
private EmployeeService employeeService;
public EmployeeController(EmployeeService employeeService){
    this.employeeService=employeeService;
}
   
    @PostMapping("/add")
    public Employee addEmployees(@RequestBody Employee entity) {
        employeeService.addEmployee(entity);
        return entity;
    }
@GetMapping("/allEmployees")
public List<Employee> getAllEmployees() { // Return type List<Employee> hona chahiye
    return employeeService.getAllEmployees();
}
    
@GetMapping("/{id}") // Dollar sign ($) hata diya
public Optional<Employee> getEmployeeById(@PathVariable int id) {
    return employeeService.getEmployeeById(id);
}

   @PutMapping("/update/{id}")
public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
    return employeeService.updateEmployee(id, employeeDetails);
}
@DeleteMapping("/delete/{id}")
public String deleteEmployee(@PathVariable int id){
    return employeeService.deleteEmployeeById(id);
    
}
}
