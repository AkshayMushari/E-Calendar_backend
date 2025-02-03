package com.evernorth.ecalender.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    private Date date;

    @Column(name = "`leave`", nullable = false)
    private boolean leave;

    @Enumerated(EnumType.STRING)
    private EventType eventType;  // New field for event type (e.g., meeting, leave, work)

    private String leaveType;  // For leave types (e.g., vacation, sick)
    
    private String scheduleOfDay;  // Description of the scheduled task (e.g., "Team meeting with Client")

    private Time startTime;  // Start time for the event/meeting
    private Time endTime;    // End time for the event/meeting

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isLeave() {
        return leave;
    }

    public void setLeave(boolean leave) {
        this.leave = leave;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getScheduleOfDay() {
        return scheduleOfDay;
    }

    public void setScheduleOfDay(String scheduleOfDay) {
        this.scheduleOfDay = scheduleOfDay;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
