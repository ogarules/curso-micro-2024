package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository repository;

    @Override
    public Pet getPetById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Pet> getAllPets(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Pet addPet(Pet pet) {
        return repository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet, Long id) {
        Pet petDb = getPetById(id);
        petDb.setAge(pet.getAge());
        petDb.setName(pet.getName());
        petDb.setSpecies(pet.getSpecies());
        petDb.setTag(pet.getTag());

        return repository.save(petDb);
    }

    @Override
    public Pet deletePetById(Long id) {
        Pet petDb = getPetById(id);
        
        repository.deleteById(id);
        return petDb;
    }
}
