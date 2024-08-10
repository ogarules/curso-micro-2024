package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.models.Greeting;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting(Greeting greeting) {
        log.info("Se ejecuta el metodo getGreeting {}", greeting.getMessage());
        return "Hola mundo con el mensaje => " + greeting.getMessage();
    }
}
