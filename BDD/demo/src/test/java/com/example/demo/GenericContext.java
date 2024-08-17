package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Data
@Component
@ScenarioScope
@Scope(SCOPE_CUCUMBER_GLUE)
public class GenericContext {

    // private int num1;
    // private int num2;
    // private String operacion;
    // private int resultado;

    private Map<String, Object> data;

    public GenericContext(){
        this.data = new HashMap<>();
    }
}
