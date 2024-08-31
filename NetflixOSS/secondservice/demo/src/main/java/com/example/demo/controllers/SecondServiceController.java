package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.DummyService;
import com.example.demo.services.RestTemplateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("dummy")
@RequiredArgsConstructor
public class SecondServiceController {
    
    @Value("${server.port}")
    private String port;

    private final DummyService feignService;
    private final RestTemplateService templateService;

    @GetMapping
    public String getFromFeignClient(){
        return "Second service port => " + port + " First service port =>" + this.feignService.getDummyFromFirstService();
    }

    @GetMapping("template")
    public String getFromTemplate(){
        return "Second service port => " + port + " First service port =>" + this.templateService.getFromTemplateResponse();
    }
}
