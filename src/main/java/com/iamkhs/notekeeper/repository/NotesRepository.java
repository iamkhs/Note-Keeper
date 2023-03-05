package com.iamkhs.notekeeper.repository;

import com.iamkhs.notekeeper.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
}
