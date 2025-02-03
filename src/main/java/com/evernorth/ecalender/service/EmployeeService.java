package com.evernorth.ecalender.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);  // This returns an Optional<Employee>
    }

    public List<Employee> searchEmployees(String query) {
        return employeeRepository.search(query);
    }

    public List<Map<String, Object>> getEmployeeAttendance(Integer id) {
        return attendanceRepository.findAttendanceSummaryByEmployee(id);
    }
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public boolean authenticate(String email, String rawPassword) {
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            return employee.getCredentials().equals(rawPassword); // Compare plain text passwords
        }
        return false;
    }

    public Employee updateEmployee(Integer id, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setCredentials(employeeDetails.getCredentials());
            employee.setRole(employeeDetails.getRole());
            employee.setManager(employeeDetails.getManager());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
