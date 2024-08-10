package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.models.Pet;
import com.querydsl.core.types.Predicate;

public interface PetService {
    Pet getPetById(Long id);
    Iterable<Pet> getAllPets(Predicate predicate, Pageable pageable);
    Pet addPet(Pet pet);
    Pet updatePet(Pet pet, Long id);
    Pet deletePetById(Long id);
}
