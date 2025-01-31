package com.evernorth.ecalender.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.repository.EmployeeRepository;
import com.evernorth.ecalender.repository.ScheduleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	public List<Employee> listAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
		
	}
	public Optional<Employee> findEmployee(Integer id) {
		return employeeRepository.findById(id);
	}
	public Optional findSchedule(Integer employeeid){
		return scheduleRepository.findByEmployeeid(employeeid);
	}

}
