package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Pet;
import com.example.demo.services.PetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {
    private final PetService service;

    @PostMapping()
    public Pet addPet(@RequestBody Pet entity) {
        log.info("Adding pet {}", entity);
        
        return service.addPet(entity);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        log.info("Getting pet {}", id);
        return service.getPetById(id);
    }
    
    @GetMapping
    public Iterable<Pet> getAllPets() {
        log.info("Getting all pets");
        return service.getAllPets();
    }
    
    @DeleteMapping("/{id}")
    public void deletePetById(@PathVariable Long id){
        Pet pet = service.deletePetById(id);
        log.info("Pet deleted {}", pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet entity) {
        log.info("Updating pet {}", entity);
        return service.updatePet(entity, id);
    }
}
