package com.luv2code.springboot.cruddemo.dto;

import com.luv2code.springboot.cruddemo.entity.Department;

public record DepartmentResponseDTO(Long id, String name) {
    public DepartmentResponseDTO (Department department){
        this(department.getId(), department.getName());
    }
}
