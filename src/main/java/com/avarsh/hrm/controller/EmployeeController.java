package com.avarsh.hrm.controller;


//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.AddressDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Address;
import com.avarsh.hrm.model.Employee;
import com.avarsh.hrm.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

//    @GetMapping("/hello")
//    public String hello(){
//        return "Hello";
//    }


    @ApiResponse(responseCode = "200",description = "Received all employees")
    @Operation(summary = "Retrieve all employees")
    @GetMapping("/employees")
    public List<EmployeeDto> getEmployees(){
        List<Employee> employeeEmp = employeeService.getEmployees();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for(Employee employee : employeeEmp){ employeeDtoList.add(employeeService.convertEmpEntityToDto(employee));}
        return employeeDtoList;
    }

    @ApiResponse(responseCode = "200",description = "Received employee's details of specified id")
    @Operation(summary = "Get particular employee by id")
    @GetMapping("/employee/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);
        return employeeService.convertEmpEntityToDto(employee);
    }

    @ApiResponse(responseCode = "200",description = "New employee added")
    @Operation(summary = "Create new Employee")
    @PostMapping("/employee")
    public void addEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = employeeService.convertEmpDtoToEntity(employeeDto);
//        for (Address address : employee.getAddresses()) {
//            address.setEmployee(employee);
//        }
        employeeService.addEmployee(employee);
    }

    @ApiResponse(responseCode = "200",description = "Updated the employee's data of specified id")
    @Operation(summary = "Change an employee's details")
    @PutMapping("/employee/{id}")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable Long id){
        Employee employee = employeeService.convertEmpDtoToEntity(employeeDto);
        employee.setId(id);
        employeeService.updateEmployeeById(employee,id);
    }

    @ApiResponse(responseCode = "200",description = "Deleted the employee of specified id")
    @Operation(summary = "Delete an Employee")
    @DeleteMapping("/employee/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/employees/search")
    public List<EmployeeDto> getEmployeeByName(@RequestParam String word) {
        List<Employee> employeeList = employeeService.getEmployeeByName(word);
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDtoList.add(employeeService.convertEmpEntityToDto(employee));
        }
        return employeeDtoList;
    }

    @GetMapping("/employees/page")
    public List<EmployeeDto> getEmployeeByPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Employee> employeeList =  employeeService.getEmployeeByPages(page, size);
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDtoList.add(employeeService.convertEmpEntityToDto(employee));
        }
        return employeeDtoList;
    }
}
