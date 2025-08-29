package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.dto.CreateEmployeeRequestDTO;
import com.luv2code.springboot.cruddemo.dto.EmployeeResponseDTO;
import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeService {

    List<Employee> findAll();
    Employee save(Employee theEmployee);
    Employee findById(int theId);
    void deleteById(int theId);
    EmployeeResponseDTO getUserById(int id);
    EmployeeResponseDTO createUser(CreateEmployeeRequestDTO request);

}
