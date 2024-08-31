package com.example.demo.users.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.users.models.User;
import com.example.demo.users.repository.UserRepository;
import com.example.demo.users.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    
    private final UserService service;

    @GetMapping()
    public Iterable<User> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }
    
    @PostMapping()
    public User postMethodName(@RequestBody User entity) {
        return service.addUser(entity);
    }

    @PutMapping("/{id}")
    public User putMethodName(@PathVariable Long id, @RequestBody User entity) {
        
        return service.updateUser(id, entity);
    }
    
}
