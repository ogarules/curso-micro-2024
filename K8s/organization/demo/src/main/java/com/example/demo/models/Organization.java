package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "organization")
@Data
public class Organization {
    
    @Id
    private String id;
    private String name;
    private String address;

    private List<Department> departments = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
}
