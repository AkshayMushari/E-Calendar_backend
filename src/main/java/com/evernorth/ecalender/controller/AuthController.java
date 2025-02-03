package com.evernorth.ecalender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.LoginRequest;
import com.evernorth.ecalender.repository.EmployeeRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Allow frontend access from localhost:3000
public class AuthController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Search for the employee by email
        Optional<Employee> employee = employeeRepository.findByEmail(request.getEmail());

        if (employee.isPresent() && employee.get().getCredentials().equals(request.getPassword())) {
            // Return employee details if login is successful
            return ResponseEntity.ok().body(Map.of(
                "id", employee.get().getId(),
                "name", employee.get().getName(),
                "role", employee.get().getRole(),
                "email", employee.get().getEmail()
            ));
        }

        // Return unauthorized error if credentials do not match
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    // You can add any other endpoints here if needed, for example:
    // Registration, password reset, etc.
}
