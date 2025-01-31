package com.evernorth.ecalender.controller;
import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.HolidaysEvents;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.service.ManagerService;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
	@Autowired
	ManagerService managerService;
	@GetMapping("listall")
	public List<Object> findAll(){
		return managerService.listAllEmployees();
	}
	@GetMapping("findemployee/{id}")
	public Map<String,Object> findEmployee(@PathVariable("id") Integer id) {
		return managerService.findEmployee(id);
	}
	@GetMapping("findschedule/{date}")
	public List<Schedule> findSchedule(@PathVariable("date") Date date){
		return managerService.findScheduleOn(date);
	}
	@GetMapping("findscheduleofon/{employeeid}/{date}")
	public ResponseEntity<List<Schedule>> findScheduleOfOn(@PathVariable("employeeid") Integer employeeid,@PathVariable("date") Date date){
		List<Schedule> x=managerService.findScheduleOfOn(employeeid, date);
		if( x.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(x);
		}
		return ResponseEntity.ok(x) ;
	}

	@GetMapping("findeventbydate/{date}")
	public ResponseEntity<List<Map<String, Object>>> findevent(@PathVariable("date") Date date) {
		List<Map<String, Object>> response=managerService.findByEventDate(date);
		if (response.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    } else {
	        return ResponseEntity.ok(response);
	    }
	}
	@PostMapping("/addevent")
	public ResponseEntity<Map<String, Object>> createEvent(@RequestBody HolidaysEvents newEvent) {
		Map<String, Object> response = managerService.createEvent(newEvent);
		return ResponseEntity.status(201).body(response);
		}
		
		  @PostMapping("/addschedule") 
	public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule){
			  Schedule s=managerService.insertSchedule(schedule);
			  return ResponseEntity.ok(s);
		  }
		 
		  @GetMapping("/totalleaves/")
			 public List<Map<String, Object>> getTotalLeaves() {
				 return managerService.getTotalLeaves();
			 }
}

