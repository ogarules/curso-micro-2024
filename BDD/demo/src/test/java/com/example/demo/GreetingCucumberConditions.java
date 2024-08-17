package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.models.Greeting;
import com.example.demo.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class GreetingCucumberConditions {
    @Autowired
    GreetingContext greetingContext;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository repository;

    @Given("^Tenemos un usuario con id '(.+)'$")
    public void userWithId(Long id){
        greetingContext.setId(id);
        greetingContext.setUserName(repository.findById(id).getName());
    }

    @SneakyThrows
    @When("^el usuario desea ser saludado$")
    public void userWantsToBeGreeted(){
        String result = mvc.perform(get("/greetings/" + greetingContext.getId()))
                           .andExpect(status().isOk())
                           .andExpect(content().contentTypeCompatibleWith("application/json"))
                           .andDo(print())
                           .andReturn().getResponse().getContentAsString();

        log.info(result);
        greetingContext.setGreeting(objectMapper.readValue(result, Greeting.class));
    }
}
