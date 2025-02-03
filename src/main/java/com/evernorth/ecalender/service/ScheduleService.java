package com.evernorth.ecalender.service;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.repository.EmployeeRepository;
import com.evernorth.ecalender.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Integer id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public Schedule saveSchedule(Schedule schedule) {
        Employee existingEmployee = employeeRepository.findById(schedule.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        schedule.setEmployee(existingEmployee); // Ensure schedule is linked to an existing employee
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedulesByEmployeeId(Integer employeeId) {
        return scheduleRepository.findByEmployeeId(employeeId);
    }

    public Schedule updateSchedule(Integer id, Schedule scheduleDetails) {
        return scheduleRepository.findById(id)
                .map(schedule -> {
                    schedule.setEmployee(scheduleDetails.getEmployee());
                    schedule.setDate(scheduleDetails.getDate());
                    schedule.setLeave(scheduleDetails.isLeave());
                    schedule.setLeaveType(scheduleDetails.getLeaveType());
                    schedule.setScheduleOfDay(scheduleDetails.getScheduleOfDay());
                    schedule.setStartTime(scheduleDetails.getStartTime());
                    schedule.setEndTime(scheduleDetails.getEndTime());
                    schedule.setEventType(scheduleDetails.getEventType());
                    return scheduleRepository.save(schedule);
                }).orElse(null);
    }

    public void deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id);
    }
    
    // Method to fetch today's attendance (present and absent employees)
    public AttendanceOverview getTodaysAttendance() {
        LocalDate today = LocalDate.now(); // Get today's date
        Date sqlToday = Date.valueOf(today); // Convert to SQL Date

        // Get all schedules for today (both present and absent employees)
        List<Schedule> presentSchedules = scheduleRepository.findByDate(sqlToday).stream()
                .filter(schedule -> !schedule.isLeave()) // Filter out leave
                .collect(Collectors.toList());
        List<Schedule> absentSchedules = scheduleRepository.findByDate(sqlToday).stream()
                .filter(schedule -> schedule.isLeave()) // Only leave
                .collect(Collectors.toList());

        // Extract employee names for present and absent employees
        List<String> presentEmployees = presentSchedules.stream()
                .map(schedule -> schedule.getEmployee().getName())
                .collect(Collectors.toList());

        List<String> absentEmployees = absentSchedules.stream()
                .map(schedule -> schedule.getEmployee().getName())
                .collect(Collectors.toList());

        return new AttendanceOverview(presentEmployees, absentEmployees);
    }
}
