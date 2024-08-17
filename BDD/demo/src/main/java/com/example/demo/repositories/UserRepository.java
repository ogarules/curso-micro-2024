package com.example.demo.repositories;

import com.example.demo.models.User;

public interface UserRepository {
    User findById(Long id);
}
