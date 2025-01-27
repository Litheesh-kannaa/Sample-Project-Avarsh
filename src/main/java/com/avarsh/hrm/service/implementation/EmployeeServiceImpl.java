package com.avarsh.hrm.service.implementation;

import com.avarsh.hrm.dao.AddressDao;
import com.avarsh.hrm.dao.EmployeeDao;
//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.AddressDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.model.Address;
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
    @Autowired
    AddressDao repository1;

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElse(new Employee());


    }

    @Override
    public void addEmployee(Employee employee) {
        repository.save(employee);
    }

    @Override
    public void updateEmployeeById(@NotNull Employee employee, Long id) {
        Employee thisEmployee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found!"));
        if (thisEmployee != null) {

            thisEmployee.setFirstName(employee.getFirstName());
            thisEmployee.setLastName(employee.getLastName());
            thisEmployee.setEmail(employee.getEmail());
            thisEmployee.setDepartment(employee.getDepartment());
            thisEmployee.setSalary(employee.getSalary());

            if(employee.getAddresses() != null){
                //thisEmployee.getAddresses().clear();
                for(Address address : employee.getAddresses()){
                    address.setEmployee(thisEmployee);
                    if(!thisEmployee.getAddresses().contains(address)){
                        thisEmployee.getAddresses().add(address);
                    }
                }

                thisEmployee.setAddresses(employee.getAddresses());
            }
            repository.save(thisEmployee);
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EmployeeDto convertEmpEntityToDto(@NotNull Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setSalary(employee.getSalary());
        if(employee.getAddresses() != null){
            employeeDto.setAddressList(employee.getAddresses().stream().map(this::convertAddEntityToDto).collect(Collectors.toList()));
        }
        return employeeDto;
    }

    @Override
    public Employee convertEmpDtoToEntity(EmployeeDto employee) {
        Employee employeeEntity = new Employee();
        employeeEntity.setId(employee.getId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setDepartment(employee.getDepartment());
        employeeEntity.setSalary(employee.getSalary());
        if(employee.getAddressList() != null){
            //employeeEntity.setAddresses(employee.getAddressList().stream().map(this::convertAddDtoToEntity).collect(Collectors.toList()));
            List<Address> addresses = employee.getAddressList().stream().map(addressDto ->{
                Address address = convertAddDtoToEntity(addressDto);
                address.setEmployee(employeeEntity);
                return address;
            }).collect(Collectors.toList());
            employeeEntity.setAddresses(addresses);
        }
        return employeeEntity;
    }

    @Override
    public AddressDto convertAddEntityToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setAddressDetail(address.getAddressDetail());
        addressDto.setAddressType(address.getAddressType());
        return addressDto;
    }

    @Override
    public Address convertAddDtoToEntity(AddressDto address) {
        Address addressEntity = new Address();
        addressEntity.setAddressId(address.getAddressId());
        addressEntity.setAddressDetail(address.getAddressDetail());
        addressEntity.setAddressType(address.getAddressType());
//        addressEntity.setEmployee(employee);
        return addressEntity;
    }
}
