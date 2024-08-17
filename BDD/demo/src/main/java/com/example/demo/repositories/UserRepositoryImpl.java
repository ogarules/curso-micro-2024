package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findById(Long id) {
        return User.builder()
                   .id(1L)
                   .name("oga")
                   .build();
    }
    
}
