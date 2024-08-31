package com.example.demo.services;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestTemplateService {
    private final RestTemplate template;

    @CircuitBreaker(name="dummyservicetemplate", fallbackMethod = "fallbackTemplate")
    public String getFromTemplateResponse(){
        URI uri = URI.create("http://localhost:8091/dummy");

        String response = template.getForObject(uri, String.class);

        return response;
    }

    public String fallbackTemplate(Exception ex){
        return "Erooooor";
    }
}
