package com.example.demo.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Employee;

@FeignClient(name = "employee")
public interface EmployeeService {
    
    @GetMapping(value = "/organization/{organizationId}")
    List<Employee> findByOrganizationId(@PathVariable String organizationId);

}
