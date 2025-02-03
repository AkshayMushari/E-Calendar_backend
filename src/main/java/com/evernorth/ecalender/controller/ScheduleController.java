package com.evernorth.ecalender.controller;

import com.evernorth.ecalender.service.AttendanceOverview;
import com.evernorth.ecalender.service.ScheduleService;
import com.evernorth.ecalender.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedules/we")  // GET /schedules/we
    public String ehhe() {
        return "djsh";
    }

    @GetMapping("/schedules")  // GET /schedules
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/schedules/{id}")  // GET /schedules/{id}
    public Schedule getScheduleById(@PathVariable Integer id) {
        return scheduleService.getScheduleById(id);
    }

//    @PostMapping("/schedules")  // POST /schedules
//    public Schedule createSchedule(@RequestBody Schedule schedule) {
//        return scheduleService.saveSchedule(schedule);
//    }
    
    @PostMapping("/schedules")
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        System.out.println("Received Schedule: " + schedule);
        return scheduleService.saveSchedule(schedule);
    }


    @PutMapping("/schedules/{id}")  // PUT /schedules/{id}
    public Schedule updateSchedule(@PathVariable Integer id, @RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(id, schedule);
    }

    @DeleteMapping("/schedules/{id}")  // DELETE /schedules/{id}
    public void deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteSchedule(id);
    }

    // Endpoint to fetch today's attendance
    @GetMapping("/schedules/today")  // GET /schedules/today
    public AttendanceOverview getTodaysAttendance() {
        return scheduleService.getTodaysAttendance();
    }
    
    @GetMapping("/schedules/employee/{employeeId}")
    public List<Schedule> getSchedulesByEmployee(@PathVariable Integer employeeId) {
        return scheduleService.getSchedulesByEmployeeId(employeeId);
    }

}
