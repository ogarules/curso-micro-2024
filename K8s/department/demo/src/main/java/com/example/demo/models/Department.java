package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "department")
public class Department {
    
    @Id
    private String id;
    private String organizationId;

    private String name;

    private List<Employee> employees = new ArrayList<>();
}
