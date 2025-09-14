package com.luv2code.springboot.cruddemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.dto.CreateEmployeeRequestDTO;
import com.luv2code.springboot.cruddemo.dto.EmployeeResponseDTO;
import com.luv2code.springboot.cruddemo.entity.Department;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Employee save(Employee theEmployee, String departmentName) {
        if (theEmployee.getId() == 0) {
            if (employeeRepository.findByEmail(theEmployee.getEmail()).isPresent()) {
                throw new RuntimeException("Employee with email already exists: " + theEmployee.getEmail());
            }
        }
        Department department = departmentService.getDepartmentByName(departmentName);
        theEmployee.setDepartment(department);
        return employeeRepository.save(theEmployee);
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

    public EmployeeResponseDTO createUser(CreateEmployeeRequestDTO request) {
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());

        Employee saved = employeeRepository.save(employee);
        return toResponse(saved);
    }

    private EmployeeResponseDTO toResponse(Employee employee) {
        return new EmployeeResponseDTO(employee.getFirstName(), employee.getEmail(), employee.getId());
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> getEmployeesByDepartment(Long departmentId, Pageable pageable) {
        // ensures department exists, will throw if not found
        departmentService.getDepartmentById(departmentId);
        return employeeRepository.findByDepartmentId(departmentId, pageable);
    }
}
