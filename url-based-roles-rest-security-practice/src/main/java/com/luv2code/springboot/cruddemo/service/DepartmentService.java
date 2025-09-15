package com.luv2code.springboot.cruddemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luv2code.springboot.cruddemo.entity.Department;
import com.luv2code.springboot.cruddemo.entity.Employee;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department getDepartmentById(Long id);
    Department getDepartmentByName(String name);
    Page<Department> getAllDepartments(Pageable pageable);
    Department updateDepartment(Long id, Department departmentDetails);
    void deleteDepartment(Long id);
}
