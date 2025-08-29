package com.luv2code.springboot.cruddemo.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.dto.CreateEmployeeRequestDTO;
import com.luv2code.springboot.cruddemo.dto.EmployeeResponseDTO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = employeeRepository.save(theEmployee);
        return dbEmployee;
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

    public EmployeeResponseDTO getUserById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find employee id - " + id));
        return toResponse(employee);
    }

    public EmployeeResponseDTO createUser(CreateEmployeeRequestDTO request){
        Employee employee = new Employee();
        employee.setId(request.id());
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());

        Employee saved = employeeRepository.save(employee);
        return toResponse(saved);
    }

    private EmployeeResponseDTO toResponse(Employee employee) {
        return new EmployeeResponseDTO(employee.getFirstName(), employee.getEmail());
    }
}
