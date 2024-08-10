package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Greeting;
import com.example.demo.services.GreetingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@RequestMapping("/greeting")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService service;

    // public GreetingController(GreetingService service){
    //     this.service = service;
    // }

    // @Autowired
    // GreetingService service;

    //@RequestMapping(value = "/hello", method=RequestMethod.POST)
    @PostMapping("/hello")
    public String processGreeting(@RequestBody Greeting greeting) {
        log.info("Calling service");
        return service.getGreeting(greeting);
    }
    
    @GetMapping("/hello/{message}")
    public String getMethodName(@PathVariable String message) {
        log.info("Returning from controller");
        return "Hello from controller => " + message;
    }
}
