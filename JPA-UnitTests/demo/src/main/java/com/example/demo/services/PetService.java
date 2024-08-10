package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Pet;

public interface PetService {
    Pet getPetById(Long id);
    Iterable<Pet> getAllPets();
    Pet addPet(Pet pet);
    Pet updatePet(Pet pet, Long id);
    Pet deletePetById(Long id);
}
