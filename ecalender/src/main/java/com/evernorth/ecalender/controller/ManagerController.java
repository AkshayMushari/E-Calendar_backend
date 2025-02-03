package com.evernorth.ecalender.controller;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.HolidaysEvents;
import com.evernorth.ecalender.entity.Schedule;
import com.evernorth.ecalender.service.ManagerService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager/listall")  // GET /manager/listall
    public List<Employee> findAllEmployees() {
        return managerService.listAllEmployees();
    }

    @GetMapping("/manager/findemployee/{id}")  // GET /manager/findemployee/{id}
    public ResponseEntity<Map<String, Object>> findEmployee(@PathVariable("id") Integer id) {
        Map<String, Object> employee = managerService.findEmployee(id);
        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/manager/findschedule/{date}")  // GET /manager/findschedule/{date}
    public ResponseEntity<List<Schedule>> findSchedule(@PathVariable("date") Date date) {
        List<Schedule> schedules = managerService.findScheduleOn(date);
        if (schedules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(schedules);
        }
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/manager/findscheduleofon/{employeeid}/{date}")  // GET /manager/findscheduleofon/{employeeid}/{date}
    public ResponseEntity<List<Schedule>> findScheduleOfOn(@PathVariable("employeeid") Integer employeeId,
                                                           @PathVariable("date") Date date) {
        List<Schedule> schedules = managerService.findScheduleOfOn(employeeId, date);
        if (schedules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(schedules);
        }
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/manager/findeventbydate/{date}")  // GET /manager/findeventbydate/{date}
    public ResponseEntity<List<HolidaysEvents>> findEvent(@PathVariable("date") Date date) {
        List<HolidaysEvents> events = managerService.findByEventDate(date);
        if (events.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(events);
        }
        return ResponseEntity.ok(events);
    }

    @PostMapping("/manager/addevent")  // POST /manager/addevent
    public ResponseEntity<HolidaysEvents> createEvent(@RequestBody HolidaysEvents newEvent) {
        HolidaysEvents event = managerService.createEvent(newEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @PostMapping("/manager/addschedule")  // POST /manager/addschedule
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = managerService.insertSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    @GetMapping("/manager/totalleaves")  // GET /manager/totalleaves
    public ResponseEntity<List<Map<String, Object>>> getTotalLeaves() {
        List<Map<String, Object>> totalLeaves = managerService.getTotalLeaves();
        if (totalLeaves.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(totalLeaves);
        }
        return ResponseEntity.ok(totalLeaves);
    }
    
 // ManagerController.java
    @GetMapping("/manager/{managerId}/team")
    public ResponseEntity<List<Employee>> getTeamByManager(@PathVariable Integer managerId) {
        List<Employee> team = managerService.getEmployeesByManager(managerId);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/manager/{managerId}/attendance")
    public ResponseEntity<Map<String, Object>> getTeamAttendance(
        @PathVariable Integer managerId,
        @RequestParam String startDate,
        @RequestParam String endDate) {
        Map<String, Object> attendance = managerService.getTeamAttendance(managerId, startDate, endDate);
        return ResponseEntity.ok(attendance);
    }

   
}
