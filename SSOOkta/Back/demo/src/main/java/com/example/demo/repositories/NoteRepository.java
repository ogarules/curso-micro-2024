package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Note;
import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByUserId(String userId);
    
}
