package com.evernorth.ecalender.repository;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evernorth.ecalender.entity.HolidaysEvents;
@Repository
public interface HolidaysEventsRepository extends CrudRepository<HolidaysEvents,Integer>{

	//Optional<HolidaysEvents> findByDate(Date date);

	//Optional<HolidaysEvents> findHolidaysEventsByDate(Date date);

	List<HolidaysEvents> findByEventDate(Date date);

	//List<HolidaysEvents> findAll();

}
