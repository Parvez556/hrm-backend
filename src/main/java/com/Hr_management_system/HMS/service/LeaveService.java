package com.Hr_management_system.HMS.service;

import org.springframework.stereotype.Service;

import com.Hr_management_system.HMS.model.LeaveApplication;
import com.Hr_management_system.HMS.model.LeaveStatus;
import com.Hr_management_system.HMS.repository.LeaveRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    

    private LeaveRepository leaveRepository;
    public LeaveService(LeaveRepository leaveRepository){
        this.leaveRepository=leaveRepository;
    }

    // 1. Employee: Apply for leave
    public LeaveApplication applyLeave(LeaveApplication leave) {
        leave.setStatus(LeaveStatus.PENDING); // Force default pending status
        return leaveRepository.save(leave);
    }

    // 2. Employee: Update leave (Only if pending)
    public LeaveApplication updateLeave(Long id, LeaveApplication updatedLeave) {
        Optional<LeaveApplication> existingLeaveOpt = leaveRepository.findById(id);
        
        if (existingLeaveOpt.isPresent()) {
            LeaveApplication existingLeave = existingLeaveOpt.get();
            
            // Business rule: Can't edit if HR already processed it
            if (existingLeave.getStatus() == LeaveStatus.PENDING) {
                existingLeave.setStartDate(updatedLeave.getStartDate());
                existingLeave.setEndDate(updatedLeave.getEndDate());
                existingLeave.setLeaveType(updatedLeave.getLeaveType());
                existingLeave.setReason(updatedLeave.getReason());
                return leaveRepository.save(existingLeave);
            } else {
                throw new RuntimeException("Cannot update leave application. It is already processed by HR.");
            }
        }
        throw new RuntimeException("Leave application not found.");
    }

    // 3. Employee: Get history by Employee ID
    public List<LeaveApplication> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

    // 4. HR: Get all leaves applied across the company
    public List<LeaveApplication> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // 5. HR: Filter leaves by status
    public List<LeaveApplication> getLeavesByStatus(LeaveStatus status) {
        return leaveRepository.findByStatus(status);
    }

    // 6. HR: Approve or Reject leave
    public LeaveApplication updateLeaveStatus(Long id, LeaveStatus status) {
        LeaveApplication leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave application not found."));
        leave.setStatus(status);
        return leaveRepository.save(leave);
    }
}