package com.avarsh.hrm.dto;

import com.avarsh.hrm.model.Employee;
import lombok.Data;

@Data
public class EmployeeDto extends Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;

}
