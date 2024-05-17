package com.example.HomeTask19.service.service.impl;

import com.example.HomeTask19.data.entity.NoteEntity;
import com.example.HomeTask19.data.repository.NoteRepository;
import com.example.HomeTask19.service.dto.NoteDto;
import com.example.HomeTask19.service.exeption.NoteNotFoundException;
import com.example.HomeTask19.service.mapper.NoteMapper;
import com.example.HomeTask19.service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImplement implements NoteService {

	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private NoteMapper noteMapper;

	@Override
	public List<NoteDto> listAll() {
		return noteMapper.toNoteDtos(noteRepository.findAll());
	}

	@Override
	@Transactional
	public NoteDto add(NoteDto note) {
		NoteEntity entity = noteMapper.toNoteEntity(note);
		entity.setId(null);
		entity.setCreateDate(LocalDate.now());
		entity.setLastUpdateDate(LocalDate.now());
		return noteMapper.toNoteDto(noteRepository.save(entity));
	}

	@Override
	@Transactional
	public void deleteById(UUID id) throws NoteNotFoundException {
		getById(id);
		noteRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void update(NoteDto note) throws NoteNotFoundException {
		if (Objects.isNull(note.getId())) {
			throw new NoteNotFoundException();
		}
		getById(note.getId());
		note.setLastUpdatedDate(LocalDate.now());
		noteRepository.save(noteMapper.toNoteEntity(note));
	}

	@Override
	public NoteDto getById(UUID id) throws NoteNotFoundException {
		Optional<NoteEntity> optionalNote = noteRepository.findById(id);
		if (optionalNote.isPresent()) {
			return noteMapper.toNoteDto(optionalNote.get());
		} else {
			throw new NoteNotFoundException(id);
		}
	}

	@Override
	public NoteDto getByTitle(String title) throws NoteNotFoundException {
		NoteEntity noteEntity =noteRepository.findByTitle(title)
				.orElseThrow(NoteNotFoundException::new);
		return noteMapper.toNoteDto(noteEntity);
	}

}
