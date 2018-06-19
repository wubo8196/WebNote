package com.web.bo;

import org.springframework.transaction.annotation.Transactional;

import com.web.dao.NoteTypeDao;
import com.web.po.NoteType;

@Transactional
public class NoteTypeBoImpl implements NoteTypeBo {

	private NoteTypeDao noteTypeDao;
	public void setNoteTypeDao(NoteTypeDao noteTypeDao) {
		this.noteTypeDao = noteTypeDao;
	}
	@Override
	public NoteType getNoteTypeById(Long id) {
		return noteTypeDao.getNoteTypeById(id);
	}

}
