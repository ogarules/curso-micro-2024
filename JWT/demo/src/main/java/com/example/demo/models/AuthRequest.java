package com.example.demo.models;

import lombok.Data;

@Data
public class AuthRequest {
    private String user;
    private String password;
}
