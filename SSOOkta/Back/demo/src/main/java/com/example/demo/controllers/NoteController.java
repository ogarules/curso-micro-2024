package com.example.demo.controllers;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.NoteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import com.example.demo.models.Note;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    
    private final NoteRepository repository;

    @GetMapping
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping("/user")
    public List<Note> getNotesFromAuthenticatedUser(Principal user) {
        return repository.findByUserId(user.getName());
    }
    
    @PostMapping
    public Note addNote(@RequestBody Note entity) {
        
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Integer id, @RequestBody Note entity, Principal user) {
        entity.setUserId(user.getName());
        
        return repository.save(entity);
    }
    
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Integer id){
        repository.deleteById(id);
    }
}
