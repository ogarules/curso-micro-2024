package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    
}
