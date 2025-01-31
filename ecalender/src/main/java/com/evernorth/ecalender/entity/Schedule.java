package com.evernorth.ecalender.entity;

import java.sql.Date;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "employeeid", referencedColumnName = "id") Employee
	 * employee; public Employee getEmployee() { return employee; } public void
	 * setEmployee(Employee employee2) { this.employee = employee2; }
	 */
	Integer employeeid; 
	Date date;
	/*@Column(name = "`leave`")
	boolean leave; */
	@Column(name = "`leave`", nullable = false) // ✅ Escaping reserved keyword
    private boolean leave;
	String leaveType;
	String scheduleOfDay;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
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
