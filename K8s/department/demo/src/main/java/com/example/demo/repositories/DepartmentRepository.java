package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Department;
import java.util.List;


@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {

    List<Department> findByOrganizationId(String organizationId);
    
}
