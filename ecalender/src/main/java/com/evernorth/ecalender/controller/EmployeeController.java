package com.evernorth.ecalender.controller;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.HolidaysEvents;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.service.EmployeeService;

@RestController
public class EmployeeController {
	 @Autowired
	   EmployeeService employeeService;
	 
	 @GetMapping("/events")
	    public Iterable<HolidaysEvents> getAllEvents() {
	        return employeeService.getAllEvents();
	    }
	 @GetMapping("/events/{date}")
	    public List<HolidaysEvents> getEventsByDate(@RequestParam String date) {
		 Date eventDate = java.sql.Date.valueOf(LocalDate.parse(date));// Convert String to LocalDate
	        return employeeService.getEventsByDate(eventDate);
	    }
	 @GetMapping("/schedule/{date}")
	    public List<Map<String, Object>> getSchedulesByDate(@RequestParam String date) {
		 Date scheduleDate = java.sql.Date.valueOf(LocalDate.parse(date));// Convert String to LocalDate
	        return employeeService.getSchedulesByDate(scheduleDate);
	    }
	 @GetMapping("/leaveshistory/{employeeid}")
	    public List<Map<String, Object>> getTotalLeavesByEmployee(@PathVariable Integer employeeid) {
	        return employeeService.getEmployeeLeaveDetails(employeeid);
	    }
	 @GetMapping("/totalleaves/{employeeid}")
	 public List<Map<String, Object>> getTotalLeaves(@PathVariable Integer employeeid) {
		 return employeeService.getTotalLeaves(employeeid);
	 }
	 @PostMapping("/schedule")
	    public Schedule addSchedule(@RequestBody Schedule schedule) {
	        return employeeService.saveSchedule(schedule);
	    }
	 @PostMapping("/addemployee")
	    public Employee addEmployee(@RequestBody Employee employee) {
	        return employeeService.saveEmployee(employee);
	        
	    }
	 @PostMapping("/authenticate")
	    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
	        boolean isAuthenticated = employeeService.authenticate(email, password);
	        if (isAuthenticated) {
	            return ResponseEntity.ok("Login successful");
	        } else {
	            return ResponseEntity.status(401).body("Invalid email or password");
	        }
}}
