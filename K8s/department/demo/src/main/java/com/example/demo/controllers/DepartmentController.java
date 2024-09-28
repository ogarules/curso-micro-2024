package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Department;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
@RequiredArgsConstructor
public class DepartmentController {
    
    private final DepartmentRepository repository;
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable String id) {
        log.info("k8s department geting department {}", id);
        return repository.findById(id).orElseThrow();
    }

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        log.info("K8s department getting all edpartments");
        return repository.findAll();
    }
    
    @GetMapping("/organization/{id}/with-employees")
    public Iterable<Department> getDepartmentsByOrganization(@PathVariable String id) {
        log.info("k8s departments getting departments for organization {}", id);

        List<Department> departments = repository.findByOrganizationId(id);
        departments.forEach(d -> d.setEmployees(employeeService.findByDepartmentId(d.getId())));

        return departments;
    }
    
    @PostMapping
    public Department addDepartment(@RequestBody Department entity) {
        log.info("k8s adding department {}", entity);
        
        return repository.save(entity);
    }
    
}
