package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Department {
    private String id;
    private String organizationId;

    private String name;

    private List<Employee> employees = new ArrayList<>();
}
