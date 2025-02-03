package com.evernorth.ecalender.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    
    @Query(value = """
        SELECT date, status 
        FROM attendance 
        WHERE employee_id = :employeeId 
        ORDER BY date DESC
        """, nativeQuery = true)
    List<Map<String, Object>> findAttendanceSummaryByEmployee(
        @Param("employeeId") Integer employeeId
    );
}