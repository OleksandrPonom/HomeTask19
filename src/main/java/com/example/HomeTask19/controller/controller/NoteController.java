package com.example.HomeTask19.controller.controller;

import com.example.HomeTask19.service.dto.NoteDto;
import com.example.HomeTask19.service.exeption.NoteNotFoundException;
import com.example.HomeTask19.service.mapper.NoteMapper;
import com.example.HomeTask19.service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/note")
public class NoteController {

	@Autowired private NoteService noteService;
	@Autowired private NoteMapper noteMapper;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNote(
			@RequestParam(value="title") String title,
			@RequestParam(value="content") String content) {
		NoteDto newNote = new NoteDto();
		newNote.setTitle(title);
		newNote.setContent(content);
		noteService.add(newNote);
		return homePage();
	}

	@GetMapping(value = "/list")
	public ModelAndView noteList() {
		ModelAndView result = new ModelAndView("allNotes");
		result.addObject("notes", noteMapper.toNoteResponses(noteService.listAll()));
		return result;
	}

	public ModelAndView homePage() {
		return new ModelAndView( "redirect:/note/list");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getNoteForEdit(@RequestParam(value="id") UUID id) throws NoteNotFoundException {
		ModelAndView result = new ModelAndView("updatesNotes");
		result.addObject("note", noteService.getById(id));
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView updateNote(
			@RequestParam(value="id") String id,
			@RequestParam(value="title") String title,
			@RequestParam(value="content") String content) throws NoteNotFoundException {
		NoteDto newNote = new NoteDto();
		newNote.setId(UUID.fromString(id));
		newNote.setTitle(title);
		newNote.setContent(content);
		noteService.update(newNote);
		return homePage();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView getNoteForDelete(@RequestParam(value="id") UUID id) throws NoteNotFoundException {
		ModelAndView result = new ModelAndView("delete");
		result.addObject("note", noteService.getById(id));
		return result;
	}

	@DeleteMapping("/delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteNoteForId (@RequestParam(value="id") UUID id) throws NoteNotFoundException {
		noteService.deleteById(id);
		return homePage();
	}

}
