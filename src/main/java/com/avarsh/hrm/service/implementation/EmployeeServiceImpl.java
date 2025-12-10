package com.avarsh.hrm.service.implementation;

import com.avarsh.hrm.dao.AddressDao;
import com.avarsh.hrm.dao.EmployeeDao;
//import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.dto.AddressDto;
import com.avarsh.hrm.dto.EmployeeDto;
import com.avarsh.hrm.exception.EmployeeNotFoundException;
import com.avarsh.hrm.model.Address;
import com.avarsh.hrm.model.Employee;
import com.avarsh.hrm.service.EmployeeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
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
        //Optional<Employee> employee =  repository.findById(id);

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("cannot find the Employee with Id: " + id));

            //throw new EmployeeNotFoundException("cannot find the Employee with Id: " + id);


    }

    @Override
    public void addEmployee(Employee employee) {
        repository.save(employee);
    }

    @Override
    public void updateEmployeeById(@NotNull Employee employee, Long id) {
        Employee existingEmployee = repository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("cannot find the Employee with Id: " + id));


        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        List<Address> currentAddresses = existingEmployee.getAddresses();
        List<Address> incomingAddresses = employee.getAddresses();

        if (incomingAddresses == null) {
            incomingAddresses = new ArrayList<>();
        }

        Map<Long, Address> currAddByIdMap = new HashMap<>();
        for (Address addr : currentAddresses) {
            if (addr.getAddressId() != null) {
                currAddByIdMap.put(addr.getAddressId(), addr);
            }
        }
        List<Address> mergedList = new ArrayList<>();
        for (Address inc : incomingAddresses) {
            Long incId = inc.getAddressId();
            if (incId != null && currAddByIdMap.containsKey(incId)) {
                Address existingAddr = currAddByIdMap.get(incId);
                existingAddr.setAddressDetail(inc.getAddressDetail());
                existingAddr.setAddressType(inc.getAddressType());
                mergedList.add(existingAddr);
            } else {
                Address newAddr = new Address();
                newAddr.setAddressType(inc.getAddressType());
                newAddr.setAddressDetail(inc.getAddressDetail());
                newAddr.setEmployee(existingEmployee);
                mergedList.add(newAddr);
            }
        }
        existingEmployee.getAddresses().clear();
        for (Address addr : mergedList) {
            existingEmployee.getAddresses().add(addr);
        }
        repository.save(existingEmployee);
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

    @Override
    public List<Employee> getEmployeeByName(String word) {
        String newWord = word.toLowerCase();
        List<Employee> employeeList = repository.findAll();
        List<Employee> newEmployeeList = new ArrayList<>();
        for (Employee employee : employeeList) {
            String firstName = employee.getFirstName().toLowerCase();
            String lastName = employee.getLastName().toLowerCase();
            if (firstName.contains(newWord) || lastName.contains(newWord)) {
                newEmployeeList.add(employee);
            }
        }
        return newEmployeeList;
    }

    @Override
    public Page<Employee> getEmployeeByPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }


}
