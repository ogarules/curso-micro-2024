package com.example.demo.users.services;

import org.springframework.stereotype.Service;

import com.example.demo.users.models.User;
import com.example.demo.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;
    private final NotificationService notificationService;

    public Iterable<User> getAll() {
        return repository.findAll();
    }
    
    public User getUser(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    public User addUser(User entity) {
        User dbUser = this.repository.save(entity);
        notificationService.sendUserMessage(dbUser);
        return dbUser;
    }

    public User updateUser(Long id, User entity){
        User userdb = repository.findById(id).orElse(null);

        userdb.setDob(entity.getDob());
        userdb.setEmail(entity.getEmail());
        userdb.setLastName(entity.getLastName());
        userdb.setName(entity.getName());
        userdb.setPhone(entity.getPhone());
        userdb = repository.save(userdb);

        notificationService.sendUserMessage(userdb);
        return userdb;
    }
}
