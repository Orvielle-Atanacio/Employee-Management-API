package com.luv2code.springboot.cruddemo.dto;

// A Data Transfer Object (DTO) used for creating a new employee.
// Records are immutable (data cannot be changed after creation), making them perfect for DTOs.
// This DTO defines the exact structure of the data the API expects from a client (like a web form or frontend) when creating an employee.
// Using a DTO shields the internal Employee Entity from direct exposure to the API.
public record CreateEmployeeRequestDTO(
        // The first name of the employee to be created.
        String firstName,
        // The last name of the employee to be created.
        String lastName,
        // The email address of the employee to be created.
        String email) {
}
