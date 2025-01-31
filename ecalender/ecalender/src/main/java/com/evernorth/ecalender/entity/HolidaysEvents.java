package com.evernorth.ecalender.entity;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HolidaysEvents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String organizer;
	String eventName;
	Date eventDate;
	Time eventTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Time getEventTime() {
		return eventTime;
	}
	public void setEventTime(Time eventTime) {
		this.eventTime = eventTime;
	}

}
