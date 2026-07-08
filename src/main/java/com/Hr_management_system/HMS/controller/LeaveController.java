package com.Hr_management_system.HMS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hr_management_system.HMS.model.LeaveApplication;
import com.Hr_management_system.HMS.model.LeaveStatus;
import com.Hr_management_system.HMS.service.LeaveService;

import java.util.List;

@RestController
@RequestMapping("/leaves")
@CrossOrigin(origins = "http://localhost:5173") // Connects cleanly with React
public class LeaveController {

    private LeaveService leaveService;
public LeaveController(LeaveService leaveService){
    this.leaveService=leaveService;
}
    // ==========================================
    // 🧑‍💼 EMPLOYEE ENDPOINTS
    // ==========================================

    // 1. POST: Apply for a leave
    @PostMapping("/apply")
    public ResponseEntity<LeaveApplication> applyLeave(@RequestBody LeaveApplication leave) {
        return ResponseEntity.ok(leaveService.applyLeave(leave));
    }

    // 2. PUT: Update leave details by id
    @PutMapping("/update/{id}")
    public LeaveApplication updateLeave(@PathVariable Long id, @RequestBody LeaveApplication leave) {
    
            return leaveService.updateLeave(id, leave);
    }

    // 3. GET: Fetch leave history for a specific employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveApplication>> getMyLeaves(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployee(employeeId));
    }

    // ==========================================
    // 🧑‍💼 HR ENDPOINTS
    // ==========================================

    // 4. GET: Fetch all leaves for HR view
    @GetMapping("/hr/all")
    public ResponseEntity<List<LeaveApplication>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    // 5. GET: Fetch leaves by specific status (e.g., /api/leaves/hr/filter?status=PENDING)
    @GetMapping("/hr/filter")
    public ResponseEntity<List<LeaveApplication>> getLeavesByStatus(@RequestParam LeaveStatus status) {
        return ResponseEntity.ok(leaveService.getLeavesByStatus(status));
    }

    // 6. PUT: HR approves or rejects a leave application
    @PutMapping("/hr/review/{id}")
    public ResponseEntity<LeaveApplication> reviewLeave(@PathVariable Long id, @RequestParam LeaveStatus status) {
        return ResponseEntity.ok(leaveService.updateLeaveStatus(id, status));
    }
}