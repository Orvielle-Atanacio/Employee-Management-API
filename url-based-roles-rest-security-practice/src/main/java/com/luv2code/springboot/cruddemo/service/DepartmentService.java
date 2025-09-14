package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Department;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department getDepartmentById(Long id);
    Department getDepartmentByName(String name);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department departmentDetails);
    void deleteDepartment(Long id);
}
