package com.evernorth.ecalender.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evernorth.ecalender.entity.HolidaysEvents;

public interface HolidaysEventsRepository extends CrudRepository<HolidaysEvents,Integer>{
	List<HolidaysEvents> findByEventDate(Date eventDate);
}
