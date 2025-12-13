package com.avarsh.hrm.dao;

import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    public List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String word1, String word2);
}