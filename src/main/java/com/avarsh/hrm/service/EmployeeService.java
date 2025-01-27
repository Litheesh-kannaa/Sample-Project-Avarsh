package com.avarsh.hrm.service;

//import com.avarsh.hrm.dto.EmployeeDto;
//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.AddressDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Address;
import com.avarsh.hrm.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    void addEmployee(Employee employee);

    void updateEmployeeById(Employee employee,Long id);

    void deleteEmployeeById(Long id);

    EmployeeDto convertEmpEntityToDto(Employee employee);

    Employee convertEmpDtoToEntity(EmployeeDto employee);

    AddressDto convertAddEntityToDto(Address address);

    Address convertAddDtoToEntity(AddressDto address);

}