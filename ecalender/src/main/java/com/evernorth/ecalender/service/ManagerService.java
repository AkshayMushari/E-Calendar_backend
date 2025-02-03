package com.evernorth.ecalender.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
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

@Service
public class ManagerService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private HolidaysEventsRepository holidaysEventsRepository;

    public List<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }

    public Map<String, Object> findEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Map<String, Object> employeeDetails = new HashMap<>();
            employeeDetails.put("id", employee.get().getId());
            employeeDetails.put("name", employee.get().getName());
            employeeDetails.put("email", employee.get().getEmail());
            employeeDetails.put("role", employee.get().getRole());
            employeeDetails.put("manager", employee.get().getManager());
            return employeeDetails;
        }
        return Collections.emptyMap();
    }

    public List<Schedule> findScheduleOn(Date date) {
        return scheduleRepository.findByDate(date);
    }

    public List<Schedule> findScheduleOfOn(Integer employeeId, Date date) {
        return scheduleRepository.findByEmployeeIdAndDate(employeeId, date);
    }

    public List<HolidaysEvents> findByEventDate(Date date) {
        return holidaysEventsRepository.findByEventDate(date);
    }

    public HolidaysEvents createEvent(HolidaysEvents newEvent) {
        return holidaysEventsRepository.save(newEvent);
    }

    public Schedule insertSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Map<String, Object>> getTotalLeaves() {
        List<Map<String, Object>> leaveRecords = new ArrayList<>();
        List<Schedule> allSchedules = scheduleRepository.findByLeaveTrue();

        for (Schedule schedule : allSchedules) {
            Map<String, Object> record = new HashMap<>();
            record.put("employeeId", schedule.getEmployee().getId());
            record.put("employeeName", schedule.getEmployee().getName());
            record.put("date", schedule.getDate());
            record.put("leaveType", schedule.getLeaveType());
            leaveRecords.add(record);
        }

        return leaveRecords;
    }
    public ManagerService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesByManager(Integer managerId) {
        return employeeRepository.findByManagerId(managerId);
    }

    public Map<String, Object> getTeamAttendance(Integer managerId, String startDate, String endDate) {
        List<Employee> team = getEmployeesByManager(managerId);
        Map<String, Object> result = new HashMap<>();
        
        team.forEach(employee -> {
            List<Schedule> attendance = scheduleRepository.findByEmployeeAndDateBetween(
                employee.getId(), 
                Date.valueOf(startDate), 
                Date.valueOf(endDate)
            );
            result.put(employee.getName(), attendance);
        });
        
        return result;
    }

}
