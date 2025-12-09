package com.avarsh.hrm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Transactional
@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@ToString(exclude = "employee")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String addressDetail;
    private String addressType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_employee_Id",nullable = false)
    //@JsonIgnore
    private Employee employee;
}
