package com.example.demo.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtTokenUtils;
import com.example.demo.models.AuthRequest;
import com.example.demo.models.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody AuthRequest entity) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(entity.getUser(), entity.getPassword()));

            User user = (User)authentication.getPrincipal();

            return ResponseEntity
                   .ok()
                   .header(HttpHeaders.AUTHORIZATION, jwtTokenUtils.generateToken(user))
                   .body(user);
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
}
