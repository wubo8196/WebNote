package com.web.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.web.po.NoteBook;
import com.web.po.NoteUser;

public class NoteBookDaoImpl extends HibernateDaoSupport implements NoteBookDao {

	@Override
	public void saveOneNoteBook(NoteBook noteBook) {
		this.getHibernateTemplate().save(noteBook);
		
	}

	@Override
	public NoteBook getNoteBookById(Long bookId) {
		return this.getHibernateTemplate().get(NoteBook.class, bookId);
	}

	@Override
	public NoteBook getRecycleByUser(NoteUser noteUser) {
		String hql = "from NoteBook noteBook where noteBook.noteUser.userId=? and noteBook.noteType.typeId=?";
		List<NoteBook> listNoteBook = (List<NoteBook>) this.getHibernateTemplate().find(hql, noteUser.getUserId(),1L);
		return listNoteBook==null&&listNoteBook.size()>0?null:listNoteBook.get(0);
	}

	@Override
	public void updateOneNoteBook(NoteBook updateNoteBook) {
		this.getHibernateTemplate().update(updateNoteBook);
		
	}

	@Override
	public void deleteNoteBook(NoteBook deleteNoteBook) {
		this.getHibernateTemplate().delete(deleteNoteBook);
		
	}

}
