package com.evernorth.ecalender.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.LoginRequest;
import com.evernorth.ecalender.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/welcome")
    public String func() {
    	return "hweyr ";
    }
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
    
    
    
 // Search employees with filters
    @GetMapping("/employees/search")
    public ResponseEntity<List<Employee>> searchEmployees(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String department) {
        List<Employee> results = employeeService.searchEmployees(name, department);
        return ResponseEntity.ok(results);
    }

    // Get employee attendance history
    @GetMapping("/employees/{id}/attendance")
    public ResponseEntity<List<Map<String, Object>>> getAttendanceHistory(
        @PathVariable Integer id) {
        List<Map<String, Object>> attendance = employeeService.getAttendanceHistory(id);
        return ResponseEntity.ok(attendance);
    }

	 @PostMapping("/authenticate")
	 public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		    boolean isAuthenticated = employeeService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		    
		    if (isAuthenticated) {
		        // Fetch the employee by email to get the role
		        Optional<Employee> employeeOptional = employeeService.getEmployeeByEmail(loginRequest.getEmail());
		        
		        if (employeeOptional.isPresent()) {
		            Employee employee = employeeOptional.get();
		            String role = employee.getRole();  // Get the role of the employee
		            
		            // Send a response that includes the role information
		            String roleMessage = "Login successful. Role: " + role;
		            return ResponseEntity.ok(roleMessage);
		        } else {
		            return ResponseEntity.status(401).body("Employee not found");
		        }
		    } else {
		        return ResponseEntity.status(401).body("Invalid email or password");
		    }
		}
//	 @CrossOrigin(origins = "http://localhost:3000")
	 @PostMapping("/addemployee")
	    public Employee addEmployee(@RequestBody Employee employee) {
	        return employeeService.saveEmployee(employee);
	        
	    }
}
