package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;

@Entity(name="notes")
@Data
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private String title;

    private String userId;

    @PrePersist
    private void initializeCreatedBy(){
        this.userId = SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
