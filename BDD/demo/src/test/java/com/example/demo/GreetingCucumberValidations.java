package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Then;

public class GreetingCucumberValidations {
    @Autowired
    GreetingContext context;

    @Then("^el salud solicitado '(.+)' es retornado$")
    public void greetingIsCorrect(String greet){
        assertEquals(context.getGreeting().getMessage(), greet);
    }
}
