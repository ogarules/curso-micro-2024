package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.models.Greeting;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Data
@Component
@ScenarioScope
@Scope(SCOPE_CUCUMBER_GLUE)
public class GreetingContext {
    private Long id;
    private String userName;
    private Greeting greeting;
}
