package com.luv2code.springboot.cruddemo.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luv2code.springboot.cruddemo.dto.CreateEmployeeRequestDTO;
import com.luv2code.springboot.cruddemo.dto.EmployeeResponseDTO;
import com.luv2code.springboot.cruddemo.entity.Employee;

// This interface defines the contract for the Employee Service layer.
// It declares the business logic available for the Employee entity.
// The implementation of this interface will contain the actual logic.
public interface EmployeeService {

    // Fetches a paginated list of all employees from the database.
    // Pageable object contains pagination and sorting information (page number, size, sort criteria).
    Page<Employee> getAllEmployees(Pageable pageable);

    // Saves a new employee or updates an existing one in the database.
    // Returns the saved employee entity (often with a generated ID if it's new).
    Employee save(Employee theEmployee, String deparmtentName);

    // Finds a specific employee by their unique ID.
    // Throws an exception (e.g., ResourceNotFoundException) if the employee is not found.
    Employee findById(int theId);

    // Deletes an employee from the database based on their unique ID.
    void deleteById(int theId);

    // Fetches a specific employee by ID and returns it as a Data Transfer Object (DTO).
    // Used to control the data exposed to the client, hiding internal entity details.
    EmployeeResponseDTO getUserById(int id);

    // Creates a new employee using data from a Create DTO.
    // This separates the API request structure from the internal Entity model.
    // Returns a Response DTO containing the saved employee's data for the client.
    EmployeeResponseDTO createUser(CreateEmployeeRequestDTO request);

    Page<Employee> getEmployeesByDepartment(Long departmentId, Pageable pageable);
}
