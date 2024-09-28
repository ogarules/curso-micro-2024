package com.example.demo.models;

import lombok.Data;

@Data
public class Employee {
    private String id;
    private String organizationId;
    private String departmentId;

    private String name;
    private Integer age;
    private String position;
}
