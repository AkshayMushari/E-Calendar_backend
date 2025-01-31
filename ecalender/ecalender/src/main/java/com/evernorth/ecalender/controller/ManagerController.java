package com.evernorth.ecalender.controller;
import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.service.ManagerService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
	@Autowired
	ManagerService managerService;
	@GetMapping("listall")
	public List<Employee> findAll(){
		return managerService.listAllEmployees();
	}
	@GetMapping("findemployee{id}")
	public Optional<Employee> findEmployee(@PathVariable("id") Integer id) {
		return managerService.findEmployee(id);
	}
	@GetMapping("findschedule{id}")
	public Optional<Schedule> findSchedule(@PathVariable("id") Integer id){
		return managerService.findSchedule(id);
	}

}

