package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Pet;
import com.example.demo.models.QPet;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>, QuerydslPredicateExecutor<Pet>, QuerydslBinderCustomizer<QPet> {
    
    @Override
    default void customize(QuerydslBindings bindings, QPet root) {
        bindings.bind(String.class)
           .first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}
