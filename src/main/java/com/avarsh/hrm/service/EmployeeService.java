package com.avarsh.hrm.service;

//import com.avarsh.hrm.dto.EmployeeDto;
//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.AddressDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Address;
import com.avarsh.hrm.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;


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

    List<Employee> getEmployeeByName(String word);

    Page<EmployeeDto> getEmployeeByPages(int page, int size,  String sortBy, String direction);
}