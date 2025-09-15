package com.luv2code.springboot.cruddemo.dto;

import java.util.Optional;

import com.luv2code.springboot.cruddemo.entity.Department;

public record DepartmentResponseDTO(Long id, String name) {
    public DepartmentResponseDTO(Department department) {
        this(
                Optional.ofNullable(department).map(Department::getId).orElse(null),
                Optional.ofNullable(department).map(Department::getName).orElse(null));
    }
}