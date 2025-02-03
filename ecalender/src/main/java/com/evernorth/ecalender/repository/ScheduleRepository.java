package com.evernorth.ecalender.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evernorth.ecalender.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByDate(Date date);  // Get schedules for a specific date
    List<Schedule> findByEmployeeIdAndDate(Integer employeeId, Date date);  // Get schedules for a specific employee on a specific date
    List<Schedule> findByLeaveTrue();  // Get all employees on leave
    List<Schedule> findByLeaveFalse(); // Get all employees who are not on leave
    List<Schedule> findByEmployeeId(Integer employeeId);

    @Query("SELECT s FROM Schedule s WHERE " +
           "s.employee.id = :employeeId AND " +
           "s.date BETWEEN :startDate AND :endDate")
    List<Schedule> findByEmployeeAndDateBetween(
        @Param("employeeId") Integer employeeId,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );
}