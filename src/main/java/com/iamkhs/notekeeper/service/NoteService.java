package com.iamkhs.notekeeper.service;

import com.iamkhs.notekeeper.model.Notes;
import com.iamkhs.notekeeper.repository.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NotesRepository repository;

    public NoteService(NotesRepository repository) {
        this.repository = repository;
    }

    public List<Notes> getNotes(){
        return repository.findAll();
    }

    public Optional<Notes> getNotesById(Integer id){
        return repository.findById(id);
    }

    public Notes saveNote(Notes notes){
        return repository.save(notes);
    }

    public void deleteNoteById(Integer id){
        repository.deleteById(id);
    }
}
