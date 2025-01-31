package com.evernorth.ecalender.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.evernorth.ecalender.entity.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule,Integer>{

	Optional findByEmployeeid(Integer employeeid);
	List findAllByEmployeeid(Integer employeeid);
	List findByDate(Date date);
	@Query("SELECT s.employeeid, COUNT(s) FROM Schedule s WHERE s.leave = true GROUP BY s.employeeid")
    List<Object[]> getTotalLeavesByEmployee();
}
