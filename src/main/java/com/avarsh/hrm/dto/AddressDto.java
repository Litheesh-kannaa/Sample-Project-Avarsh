package com.avarsh.hrm.dto;

import com.avarsh.hrm.model.Employee;
import lombok.Data;

@Data
public class AddressDto {
    private Long addressId;
    private String addressDetail;
    private String addressType;

}
