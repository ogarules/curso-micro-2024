package com.example.demo.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Department;

@FeignClient(name="department")
public interface DepartmentService {
    
    @GetMapping("/organization/{organizationId}")
    List<Department> findByOrganizationId(@PathVariable String organizationId);

    @GetMapping("/organization/{organizationId}/with-employees")
    List<Department> findByOrganizationWithEmployees(@PathVariable String organizationId);
}
