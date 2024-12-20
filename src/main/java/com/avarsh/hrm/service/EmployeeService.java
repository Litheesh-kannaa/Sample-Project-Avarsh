package com.avarsh.hrm.service;

//import com.avarsh.hrm.dto.EmployeeDto;
//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeDto> getEmployees();

    EmployeeDto getEmployeeById(Long id);

    void addEmployee(EmployeeDto employee);

    void updateEmployeeById(EmployeeDto employee,Long id);

    void deleteEmployeeById(Long id);

    EmployeeDto convertEntityToDto(Employee employee);
}
