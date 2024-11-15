package com.dev.manytomany.repository;

import com.dev.manytomany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.eid = :eid")
    void deleteEmployeeById(@Param("eid") Long eid);
}

