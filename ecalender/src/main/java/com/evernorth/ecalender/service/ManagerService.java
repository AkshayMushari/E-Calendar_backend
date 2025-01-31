package com.evernorth.ecalender.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.HolidaysEvents;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.repository.EmployeeRepository;
import com.evernorth.ecalender.repository.HolidaysEventsRepository;
import com.evernorth.ecalender.repository.ScheduleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	HolidaysEventsRepository holidaysEventsRepository;

	public List<Object> listAllEmployees() {

		List<Employee> list = (List<Employee>) employeeRepository.findAll();
		List<Object> l = new ArrayList<>();
		for (Employee emp : list) {
			Map<String, Object> map = new HashMap();
			map.put("id", emp.getId());
			map.put("name", emp.getName());
			map.put("email", emp.getEmail());
			l.add(map);

		}
		return l;

	}

	public Map<String, Object> findEmployee(Integer id) {
		Optional<Employee> emp1 = employeeRepository.findById(id);
		Map<String, Object> map = new HashMap();
		if (emp1.isPresent()) {
			Employee emp = emp1.get();
			map.put("id", emp.getId());
			map.put("name", emp.getName());
			map.put("email", emp.getEmail());
		} else {
			map.put("error", "employee not found");
		}
		return map;
	}

	public List<Schedule> findScheduleOn(Date date) {
		return (List<Schedule>) scheduleRepository.findAllByDate(date);
	}

	public List<Schedule> findScheduleOfOn(Integer employeeid, Date date) {
		return scheduleRepository.findScheduleByEmployeeidAndDate(employeeid, date);
	}

	public  List<Map<String, Object>> findByEventDate(Date date) {
		List<HolidaysEvents> event = holidaysEventsRepository.findByEventDate(date);
		List<Map<String, Object>> eventDetailsList = new ArrayList<>();
		/*Map<String, Object> response = new HashMap<>();
		if (event.isEmpty()) {
			response.put("message", "No Event Found");
			response.put("status", "error");
			eventDetailsList.add(response);
		}
		else {*/
for(HolidaysEvents event1:event) {
		/* else {
			response.put("event", event.getEventName());
			response.put("status", "success");
		}*/
	Map<String, Object> response1 = new HashMap<>();
	response1.put("eventDate", event1.getEventDate());
	response1.put("eventTime", event1.getEventTime());
	response1.put("eventName",event1.getEventName());
	eventDetailsList.add(response1);
	}
return eventDetailsList;
		}

	public Map<String, Object> createEvent(HolidaysEvents newEvent) {
		Map<String, Object> response = new HashMap<>();
		HolidaysEvents savedEvent = holidaysEventsRepository.save(newEvent);
		response.put("message", "Event created successfully");
		response.put("event", savedEvent);
		return response;
	}
	public Schedule insertSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}
		
    public List<Map<String, Object>> getTotalLeaves() {
        List<Object[]> leaveDetails = scheduleRepository.getTotalLeavesByEmployee();

        List<Map<String, Object>> leaveDetailsList = new ArrayList<>();
        
        for (Object[] obj : leaveDetails) {
        	
            Map<String, Object> leaveDetailsMap = new HashMap<>();
            leaveDetailsMap.put("employeeid", obj[0]);
            leaveDetailsMap.put("totalLeavesTaken", obj[1]);
            leaveDetailsList.add(leaveDetailsMap);}
    
        
        return leaveDetailsList;
    }

}
