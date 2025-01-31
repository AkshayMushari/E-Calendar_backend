package com.evernorth.ecalender.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evernorth.ecalender.entity.Employee;
import com.evernorth.ecalender.entity.HolidaysEvents;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
	
	Optional<Employee> findById(Integer id);
	 Optional<Employee> findByEmail(String email);
}
