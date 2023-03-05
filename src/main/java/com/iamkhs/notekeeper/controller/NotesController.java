package com.iamkhs.notekeeper.controller;

import com.iamkhs.notekeeper.model.Notes;
import com.iamkhs.notekeeper.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello World! Welcome to my NoteKeeper App";
    }

    @GetMapping("/notes/all")
    public List<Notes> getNotes(){
        return noteService.getNotes();
    }

    @GetMapping("/notes/{id}")
    public Notes getNoteById(@PathVariable Integer id){
        return noteService.getNotesById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNotesById(@PathVariable Integer id){
        if (noteService.getNotesById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        noteService.deleteNoteById(id);
    }

    @PostMapping("/notes")
    public Notes saveNotes(@Valid @RequestBody Notes notes){
        return noteService.saveNote(notes);
    }

    @PutMapping("/notes/{id}")
    public Notes updateNotesById(@PathVariable Integer id, @RequestBody Notes newNote){
        Optional<Notes> note = noteService.getNotesById(id);
        if (note.isPresent()){
            Notes oldNote = note.get();
            if (newNote.getTitle().isBlank() && newNote.getContent().isBlank()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if (newNote.getTitle().isBlank()) {
                newNote.setTitle(oldNote.getTitle());
            }
            if (newNote.getContent().isBlank()) {
                newNote.setContent(oldNote.getContent());
            }
            oldNote.setTitle(newNote.getTitle());
            oldNote.setContent(newNote.getContent());
            return noteService.saveNote(oldNote);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
