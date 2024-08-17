package com.example.demo;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.GreetingController;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@CucumberContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GreetingApiTests {
    
    @Autowired
    MockMvc mvc;

    @Autowired
    GreetingContext greetingContext;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserRepository repository;

    @InjectMocks
    @Autowired
    GreetingController controller;

    @Before
    public void setupMocks(){
        given(repository.findById(any()))
            .willReturn(User.builder()
                            .id(2L)
                            .name("Test")
                            .build());
    }
}
