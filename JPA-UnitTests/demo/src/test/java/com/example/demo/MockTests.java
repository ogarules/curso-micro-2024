package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.PetController;
import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.example.demo.services.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = DemoApplication.class)
public class MockTests {
    
    @MockBean
    PetRepository repository;

    @InjectMocks
    @Autowired
    PetController controller;

    @Autowired
    PetService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void serviceAddPet(){
        log.info("Sdding pet...");
        
        Pet petresponse = Pet.builder()
        .id(10L)
		.age(2)
		.name("memo")
		.species("Perrito")
		.tag("123.4321")
		.build();
        
        given(repository.save(any())).willReturn(petresponse);

        Pet pet = Pet.builder()
		.age(2)
		.name("memo")
		.species("Perrito")
		.tag("123.4321")
		.build();

        Pet petDB = service.addPet(pet);

        assertEquals(petDB.getId(), 10L);
    }

    @SneakyThrows
    @Test
    public void mockAdd(){
        log.info("Probando agreagr mascotas con un mock repository");

        Pet pet = Pet.builder()
		.age(2)
		.name("memo")
		.species("Perrito")
		.tag("123.4321")
		.build();

        Pet petresponse = Pet.builder()
        .id(10L)
		.age(2)
		.name("memo")
		.species("Perrito")
		.tag("123.4321")
		.build();

        given(repository.save(any())).willReturn(petresponse);

        String payload = objectMapper.writeValueAsString(pet);

        String result = mvc.perform(post("/pets").contentType("application/json").content(payload))
		                   .andExpect(status().isOk())
						   .andExpect(content().contentTypeCompatibleWith("application/json"))
						   .andDo(print())
						   .andExpect(jsonPath("$.id", is(10)))
						   .andReturn().getResponse().getContentAsString();

		log.info("Resultado => {}", result);
    }
}
