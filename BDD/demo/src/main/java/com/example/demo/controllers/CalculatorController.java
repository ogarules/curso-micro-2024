package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    
    @GetMapping("/sum/{num1}/{num2}")
    public int add(@PathVariable int num1, @PathVariable int num2) {
        return num1 + num2;
    }
    
    @GetMapping("/substract/{num1}/{num2}")
    public int substract(@PathVariable int num1, @PathVariable int num2) {
        return num1 - num2;
    }
    
    @GetMapping("/multiply/{num1}/{num2}")
    public int multiply(@PathVariable int num1, @PathVariable int num2) {
        return num1 * num2;
    }
    
}
