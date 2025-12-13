package com.avarsh.hrm.dto;

import com.avarsh.hrm.model.Address;
import com.avarsh.hrm.model.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;

    private List<AddressDto> addressList;

//    public List<AddressDto> getAddressList() {
//        return addressList;
//    }
}
