package com.example.demo.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.models.User;

@Service
public class UserService {
    
    public Optional<UserDetails> findByUserName(String userName){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return Optional.ofNullable(User.builder()
                                       .username("oga")
                                       .password(encoder.encode("pass"))
                                       .email("oga@oga.com")
                                       .build());
    }
}
