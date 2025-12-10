package com.avarsh.hrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

//@Transactional
@Data
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@ToString(exclude = "addresses")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String department;
    private Double salary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", orphanRemoval = true)
//    @JoinColumn(name = "fk_empId", referencedColumnName = "id")
    @JsonIgnore
    private List<Address> addresses;
}
