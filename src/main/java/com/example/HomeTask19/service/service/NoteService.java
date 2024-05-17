package com.example.HomeTask19.service.service;

import com.example.HomeTask19.service.dto.NoteDto;
import com.example.HomeTask19.service.exeption.NoteNotFoundException;

import java.util.List;
import java.util.UUID;

public interface NoteService {

	List<NoteDto> listAll();

	NoteDto add(NoteDto note);

	void deleteById(UUID id) throws NoteNotFoundException;

	void update(NoteDto note) throws NoteNotFoundException;

	NoteDto getById(UUID id) throws NoteNotFoundException;

	NoteDto getByTitle(String title) throws NoteNotFoundException;
}