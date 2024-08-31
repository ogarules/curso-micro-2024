package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("dummy")
public class DummyController {
    
    @Value("${server.port}")
    private String port;

    @GetMapping
    public String getPort() {
        return "puerto del first service => " + port;
    }
    
}
