package com.evernorth.ecalender.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer employeeid; 
	Date date;
	@Column(name = "`leave`", nullable = false) // ✅ Escaping reserved keyword
    private boolean leave;
	String leaveType;
	String scheduleOfDay;
	public Integer getId() {
		return employeeid;
	}
	public void setId(Integer id) {
		this.employeeid = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean getLeave() {
		return leave;
	}
	public void setLeave(boolean leave) {
		this.leave = leave;
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
	

}
