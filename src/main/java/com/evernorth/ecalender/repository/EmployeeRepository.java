package com.evernorth.ecalender.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evernorth.ecalender.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
//    @Query("SELECT e FROM Employee e WHERE e.managerId = :managerId")
//    List<Employee> findByManagerId(@Param("managerId") Integer managerId);
//	 Optional Employee
	 Optional<Employee> findByEmail(String email);
	 List<Employee> findByManager_Id(Integer managerId);
	    
	    // Alternative JPQL implementation
	    @Query("SELECT e FROM Employee e WHERE e.manager.id = :managerId")
	    List<Employee> findByManagerId(@Param("managerId") Integer managerId);
	    
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(e.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Employee> search(@Param("query") String query);
}