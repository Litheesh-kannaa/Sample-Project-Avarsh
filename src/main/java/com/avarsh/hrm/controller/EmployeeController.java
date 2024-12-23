package com.avarsh.hrm.controller;


//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Employee;
import com.avarsh.hrm.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/get-employees")
    public List<EmployeeDto> getEmployees(){
        return employeeService.getEmployees();
    }

    @ApiResponse(responseCode = "200",description = "Received employee's details of specified id")
    @Operation(summary = "Get particular employee by id")
    @GetMapping("/get-employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @ApiResponse(responseCode = "200",description = "New employee added")
    @Operation(summary = "Create new Employee")
    @PostMapping("/add-employee")
    public void addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }

    @ApiResponse(responseCode = "200",description = "Updated the employee's data of specified id")
    @Operation(summary = "Change an employee's details")
    @PutMapping("/update-employee/{id}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable Long id){
        employeeService.updateEmployeeById(employee,id);
    }

    @ApiResponse(responseCode = "200",description = "Deleted the employee of specified id")
    @Operation(summary = "Delete an Employee")
    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }
}
