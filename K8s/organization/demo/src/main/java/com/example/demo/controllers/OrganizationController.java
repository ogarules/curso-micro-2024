package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Organization;
import com.example.demo.repositories.OrganizationRepository;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationRepository repository;
    private final DepartmentService deparmentService;
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable String id) {
        log.info("k8s organization get by id {}", id);
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping
    public Iterable<Organization> getAllOrganizations() {
        log.info("k8s organization get all");
        return repository.findAll();
    }

    @GetMapping("/{id}/departments")
    public Organization getOrganizationWithDepartments(@PathVariable String id) {
        log.info("k8s get organization with departments {}", id);

        return repository.findById(id)
                                    .map(o -> {
                                        o.setDepartments(deparmentService.findByOrganizationId(id));
                                        return o;
                                    }).orElse(null);
        
    }

    @GetMapping("/{id}/departments/employees")
    public Organization getOrganizationWithDepartmentsWithEmployees(@PathVariable String id) {
        log.info("k8s get organization with departments and employees {}", id);

        return repository.findById(id)
                                    .map(o -> {
                                        o.setDepartments(deparmentService.findByOrganizationWithEmployees(id));
                                        return o;
                                    }).orElse(null);
        
    }
    
    @GetMapping("/{id}/employees")
    public Organization getOrganizationWithEmployees(@PathVariable String id) {
        log.info("k8s get organization with employees {}", id);

        return repository.findById(id)
                         .map(o -> {
                            o.setEmployees(employeeService.findByOrganizationId(id));
                            return o;
                         })
                         .orElse(null);
    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization entity) {
        log.info("k8s add organization {}", entity);

        return repository.save(entity);
    }
}
