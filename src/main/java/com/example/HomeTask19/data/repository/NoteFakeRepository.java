package com.example.HomeTask19.data.repository;

import com.example.HomeTask19.data.entity.NoteEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NoteFakeRepository {

	private final List<NoteEntity> notes = new ArrayList<>();

	public NoteEntity addNote(NoteEntity note){
		note.setId(UUID.randomUUID());
		this.notes.add(note);
		return note;
	}

	public NoteEntity updateNote(NoteEntity note){
		if(Objects.isNull(note.getId())){
			note.setId(UUID.randomUUID());
			this.notes.add(note);
		} else {
			Optional<NoteEntity> optionalNote = this.notes.stream()
					.filter(n -> n.getId().equals(note.getId()))
					.findFirst();
			if (optionalNote.isPresent()){
				this.notes.remove(optionalNote.get());
				this.notes.add(note);
			}
		}
		return note;
	}

	public List<NoteEntity> listAllNotes() {
		return this.notes;
	}

	public Optional<NoteEntity> getByIdNote(UUID id) {
		return this.notes.stream()
				.filter(note -> note.getId().equals(id))
				.findFirst();
	}

	public void deleteByIdNote(UUID id){
		this.notes.stream()
				.filter(note -> note.getId().equals(id))
				.findFirst()
				.ifPresent(this.notes::remove);
	}
}
