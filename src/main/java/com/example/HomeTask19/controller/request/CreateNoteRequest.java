package com.example.HomeTask19.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNoteRequest extends NoteRequest {
	public CreateNoteRequest () {
	}

	public CreateNoteRequest (String title, String content) {
		super(title, content);
	}
}
