package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.ExceptionHandling.EmployeeNotFoundException;
import com.luv2code.springboot.cruddemo.dto.CreateEmployeeRequestDTO;
import com.luv2code.springboot.cruddemo.dto.EmployeeResponseDTO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<EmployeeResponseDTO> findAll() {
        return employeeService.findAll().stream()
                .map(emp -> new EmployeeResponseDTO(emp.getFirstName(), emp.getEmail())).toList();
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody CreateEmployeeRequestDTO theEmployee) {
        EmployeeResponseDTO response = employeeService.createUser(theEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> createUser(@PathVariable int employeeId) {
        return ResponseEntity.ok(employeeService.getUserById(employeeId));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable int id,
            @RequestBody CreateEmployeeRequestDTO dto) {
        Employee employee = employeeService.findById(id); // fetch existing
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setEmail(dto.email());

        Employee updated = employeeService.save(employee);
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO(updated.getFirstName(), updated.getEmail());
        return ResponseEntity.ok(responseDTO);
    }
}
