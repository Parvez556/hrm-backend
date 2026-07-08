package com.Hr_management_system.HMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hr_management_system.HMS.model.LeaveApplication;
import com.Hr_management_system.HMS.model.LeaveStatus;

import java.util.List;


public interface LeaveRepository extends JpaRepository<LeaveApplication, Long> {
    
    // Find leaves applied by a specific employee
    List<LeaveApplication> findByEmployeeId(Long employeeId);
    
    // Find leaves filtered by status (PENDING, APPROVED, REJECTED)
    List<LeaveApplication> findByStatus(LeaveStatus status);
}