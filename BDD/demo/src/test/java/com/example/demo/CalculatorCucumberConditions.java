package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.models.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CalculatorCucumberConditions {
    @Autowired
    GenericContext context;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Given("^Tenemos dos numeros '(.+)' y '(.+)'$")
    public void userWithId(int num1, int num2){
        context.getData().put("num1", num1);
        context.getData().put("num2", num2);
        // context.setNum1(num1);
        // context.setNum2(num2);
    }

    @SneakyThrows
    @When("^el usuario realiza la operacion (.+)$")
    public void userWantsToBeGreeted(String operacion){

        // context.setOperacion(operacion);
        String endpoint = "";

        switch (operacion) {
            case "suma":
                endpoint = "sum";
                break;
            case "resta":
                endpoint = "substract";
                break;
            case "multiplicacion":
                endpoint = "multiply";
                break;
            default:
                break;
        }

        String result = mvc.perform(get("/calculator/" + endpoint + "/" + context.getData().get("num1") + "/" + context.getData().get("num2")))
        // String result = mvc.perform(get("/calculator/" + endpoint + "/" + context.getNum1()+ "/" + context.getNum2()))
                           .andExpect(status().isOk())
                           .andExpect(content().contentTypeCompatibleWith("application/json"))
                           .andDo(print())
                           .andReturn().getResponse().getContentAsString();
        
        context.getData().put("result", Integer.parseInt(result));
        //context.setResultado(Integer.parseInt(result));
    }

    @Then("^el resultado es '(.+)'$")
    public void greetingIsCorrect(int result){
        assertEquals((int)context.getData().get("result"), result);
        //assertEquals(context.getResultado(), result);
    }
}
