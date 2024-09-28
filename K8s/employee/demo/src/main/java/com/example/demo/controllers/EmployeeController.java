package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

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
public class EmployeeController {
    
    private final EmployeeRepository repository;

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        log.info("K8s obteniendo empleado por id {}", id);
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        log.info("K8s obteniendo todos los empleados");
        return repository.findAll();
    }

    @GetMapping("/department/{id}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String id) {
        log.info("K8s obteniendo empleados por departamento {}", id);
        return repository.findByDepartmentId(id);
    }

    @GetMapping("/organization/{id}")
    public List<Employee> getEmployeesByOrganization(@PathVariable String id) {
        log.info("K8s obteniendo empleados por organizacion {}", id);
        return repository.findByOrganizationId(id);
    }
    
    @PostMapping
    public Employee addEmployee(@RequestBody Employee entity) {
        log.info("K8s agregando empleado {}", entity);
        
        return repository.save(entity);
    }
    
}
