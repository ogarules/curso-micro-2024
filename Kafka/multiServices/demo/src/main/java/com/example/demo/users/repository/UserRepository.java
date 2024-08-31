package com.example.demo.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.users.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
