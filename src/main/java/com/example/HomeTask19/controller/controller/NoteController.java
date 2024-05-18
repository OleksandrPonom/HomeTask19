package com.example.HomeTask19.controller.controller;

import com.example.HomeTask19.controller.request.CreateNoteRequest;
import com.example.HomeTask19.controller.request.UpdateNoteRequest;
import com.example.HomeTask19.controller.response.NoteResponse;
import com.example.HomeTask19.service.dto.NoteDto;
import com.example.HomeTask19.service.exeption.NoteNotFoundException;
import com.example.HomeTask19.service.mapper.NoteMapper;
import com.example.HomeTask19.service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/note")
public class NoteController {

	@Autowired private NoteService noteService;
	@Autowired private NoteMapper noteMapper;

	@PostMapping(value = "/create")
	public ResponseEntity<NoteResponse> createNote(@RequestBody CreateNoteRequest request) {
		NoteDto newNote = noteMapper.toNoteDto(request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(noteMapper.toNoteResponse(noteService.add(newNote)));
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<NoteResponse>> noteList() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(noteMapper.toNoteResponses(noteService.listAll()));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateNote(
			@PathVariable("id") UUID id,
			@RequestBody UpdateNoteRequest request) throws NoteNotFoundException {
		NoteDto newNote = noteMapper.toNoteDto(id, request);
		noteService.update(newNote);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<NoteResponse> getNoteById(@PathVariable("id") UUID id) throws NoteNotFoundException {
		NoteDto newNote = noteService.getById(id);
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(noteMapper.toNoteResponse(newNote));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteNoteForId (@PathVariable("id") UUID id) throws NoteNotFoundException {
		noteService.deleteById(id);
	}

}
