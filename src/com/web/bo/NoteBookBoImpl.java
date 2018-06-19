package com.web.bo;

import org.springframework.transaction.annotation.Transactional;

import com.web.dao.NoteBookDao;
import com.web.po.NoteBook;
import com.web.po.NoteUser;

@Transactional
public class NoteBookBoImpl implements NoteBookBo {
	private NoteBookDao noteBookDao;

	public void setNoteBookDao(NoteBookDao noteBookDao) {
		this.noteBookDao = noteBookDao;
	}

	@Override
	public void saveOneNoteBook(NoteBook noteBook) {
		noteBookDao.saveOneNoteBook(noteBook);
	}

	@Override
	public NoteBook getNoteBookById(Long bookId) {
		return noteBookDao.getNoteBookById(bookId);
	}

	@Override
	public NoteBook getRecycleByUser(NoteUser noteUser) {
		return noteBookDao.getRecycleByUser(noteUser);
	}

	@Override
	public void updateOneNoteBook(NoteBook updateNoteBook) {
		this.noteBookDao.updateOneNoteBook(updateNoteBook);
		
	}

	@Override
	public void deleteNoteBook(NoteBook deleteNoteBook) {
		this.noteBookDao.deleteNoteBook(deleteNoteBook);
		
	}

}
