package com.evernorth.ecalender.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evernorth.ecalender.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

	
	 Optional<Employee> findByEmail(String email);

}
