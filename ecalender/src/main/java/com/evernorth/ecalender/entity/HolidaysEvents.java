package com.evernorth.ecalender.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name="holidaysEvents")

public class HolidaysEvents {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "organizer")
    String organizer;

    @Column(name = "event_name")
    String eventName;

    @Column(name = "event_date")
    Date eventDate;

    @Column(name = "event_time")
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
	@Override
	public String toString() {
		return "HolidaysEvents [id=" + id + ", organizer=" + organizer + ", eventName=" + eventName + ", eventDate="
				+ eventDate + ", eventTime=" + eventTime + "]";
	}
	

}
