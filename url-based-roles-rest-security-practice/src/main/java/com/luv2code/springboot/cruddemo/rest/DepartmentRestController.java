package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dto.DepartmentRequestDTO;
import com.luv2code.springboot.cruddemo.dto.DepartmentResponseDTO;
import com.luv2code.springboot.cruddemo.entity.Department;
import com.luv2code.springboot.cruddemo.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    // Get all departments
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentResponseDTO> responseDTOs = departments.stream()
                .map(DepartmentResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // GET department by name
    @GetMapping("/id/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO(department);
        return ResponseEntity.ok(responseDTO);
    }

    // GET department by name
    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByName(@PathVariable String name) {
        Department department = departmentService.getDepartmentByName(name);
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO(department);
        return ResponseEntity.ok(responseDTO);
    }

    // CREATE new department
     @PostMapping
    @Operation(summary = "Create a new department")
    @ApiResponse(responseCode = "201", description = "Department created")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @RequestBody @Valid DepartmentRequestDTO requestDTO) { // ← Now using correct annotation
        Department department = new Department();
        department.setName(requestDTO.name());
        Department saved = departmentService.createDepartment(department);
        return new ResponseEntity<>(new DepartmentResponseDTO(saved), HttpStatus.CREATED);
    }

    // UPDATE existing department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id,
            @RequestBody @Valid DepartmentRequestDTO requestDTO) {

        // Create department entity from DTO
        Department departmentDetails = new Department();
        departmentDetails.setName(requestDTO.name());

        // Update department
        Department updateDepartment = departmentService.updateDepartment(id, departmentDetails);
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO(updateDepartment);

        return ResponseEntity.ok(responseDTO);
    }

    // DELETE department
    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}
