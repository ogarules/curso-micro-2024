package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    List<Employee> findByDepartmentId(String departmentId);
    List<Employee> findByOrganizationId(String organizationId);
    
    
}
