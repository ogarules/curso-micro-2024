package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = DemoApplication.class)
class DemoApplicationTests {

	@Autowired
	PetRepository repository;

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void repositoryAddTest() {
		log.info("Probando agregar al repositorio...");

		Pet pet = Pet.builder()
		             .age(2)
					 .name("memo")
					 .species("Perrito")
					 .tag("123.4321")
					 .build();
		
		assertNull(pet.getId());

		this.repository.save(pet);

		log.info("Mascota agregada con el id -> {}", pet.getId());

		assertNotNull(pet.getId());
	}

	@Test
	public void mvcTest() throws UnsupportedEncodingException, Exception{
		Pet pet = Pet.builder()
		.age(2)
		.name("memo")
		.species("Perrito")
		.tag("123.4321")
		.build();

		String payload = objectMapper.writeValueAsString(pet);

		String result = mvc.perform(post("/pets").contentType("application/json").content(payload))
		                   .andExpect(status().isOk())
						   .andExpect(content().contentTypeCompatibleWith("application/json"))
						   .andDo(print())
						   .andExpect(jsonPath("$.name", is("memo")))
						   .andReturn().getResponse().getContentAsString();

		log.info("Resultado => {}", result);
	}

}
