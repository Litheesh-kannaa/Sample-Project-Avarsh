package com.avarsh.hrm.service.implementation;

import com.avarsh.hrm.dao.EmployeeDao;
//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Employee;
import com.avarsh.hrm.service.EmployeeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao repository;
    @Override
    public List<EmployeeDto> getEmployees() {
        return repository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return repository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(new EmployeeDto());

    }

    @Override
    public void addEmployee(EmployeeDto employee) {
        repository.save(employee);
    }

    @Override
    public void updateEmployeeById(@NotNull EmployeeDto employee, Long id) {
        if(employee.getId().equals(id)) {
            repository.save(employee);
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EmployeeDto convertEntityToDto(@NotNull Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }


}
