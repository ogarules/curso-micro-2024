package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Greeting;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/greetings")
public class GreetingController {
    
    @Autowired
    UserService service;

    @GetMapping("/{id}")
    public Greeting getGreetingForUser(@PathVariable Long id) {
        return Greeting.builder()
            .message("HElloo " + service.getUserById(id).getName())
            .build();
    }
    
}
