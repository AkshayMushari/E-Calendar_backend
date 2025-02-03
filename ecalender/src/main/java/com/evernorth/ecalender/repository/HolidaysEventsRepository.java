package com.evernorth.ecalender.repository;

import com.evernorth.ecalender.entity.HolidaysEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HolidaysEventsRepository extends JpaRepository<HolidaysEvents, Integer> {
    List<HolidaysEvents> findByEventDate(Date date);
}
