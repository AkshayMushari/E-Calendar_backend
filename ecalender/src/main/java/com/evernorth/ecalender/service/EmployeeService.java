package com.evernorth.ecalender.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.HolidaysEvents;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.repository.EmployeeRepository;
import com.evernorth.ecalender.repository.HolidaysEventsRepository;
import com.evernorth.ecalender.repository.ScheduleRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class EmployeeService {
	@Autowired
    HolidaysEventsRepository eventRepository;
@Autowired
ScheduleRepository scheduleRepository;
@Autowired
EmployeeRepository employeeRepository;

    public Iterable<HolidaysEvents> getAllEvents() {
        return eventRepository.findAll();
    }

   
    public List<HolidaysEvents> getEventsByDate(Date date) {
        return eventRepository.findByEventDate(date);
    }
    public List<Map<String, Object>> getSchedulesByDate(Date date){
    	List<Schedule> schedules= scheduleRepository.findByDate(date);
    	List<Map<String, Object>> scheduleDetailsList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            Map<String, Object> scheduleDetails = new HashMap<>();
            scheduleDetails.put("date", schedule.getDate());
            scheduleDetails.put("schedule",schedule.getScheduleOfDay());
            
            scheduleDetailsList.add(scheduleDetails);
        }
        return scheduleDetailsList;
    }
    public List<Map<String, Object>> getEmployeeLeaveDetails(Integer employeeid) {
        // Fetch all schedule records for the employee
        List<Schedule> schedules = scheduleRepository.findAllByEmployeeid(employeeid);
        
        // Filter out only the records where 'isLeave' is true
        List<Map<String, Object>> leaveDetailsList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            Map<String, Object> leaveDetails = new HashMap<>();
            leaveDetails.put("id", schedule.getId());
            leaveDetails.put("leaveType", schedule.getLeaveType());
            leaveDetails.put("leave", schedule.getLeave());
            leaveDetails.put("date", schedule.getDate());
            leaveDetailsList.add(leaveDetails);
        }
        return leaveDetailsList;
    }
    
    public List<Map<String, Object>> getTotalLeaves(Integer employeeid) {
        List<Object[]> leaveDetails = scheduleRepository.getTotalLeavesByEmployee();

        List<Map<String, Object>> leaveDetailsList = new ArrayList<>();
        
        for (Object[] obj : leaveDetails) {
        	if(obj[0]==employeeid) {
            Map<String, Object> leaveDetailsMap = new HashMap<>();
            leaveDetailsMap.put("employeeid", obj[0]);
            leaveDetailsMap.put("totalLeavesTaken", obj[1]);
            leaveDetailsList.add(leaveDetailsMap);}
        }
        
        return leaveDetailsList;
    }
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
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
}
