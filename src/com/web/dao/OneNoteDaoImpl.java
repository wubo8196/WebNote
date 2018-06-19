package com.web.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.web.po.OneNote;

public class OneNoteDaoImpl extends HibernateDaoSupport implements OneNoteDao {

	@Override
	public void saveOneNote(OneNote oneNote) {
		this.getHibernateTemplate().save(oneNote);
		
	}

	@Override
	public void saveOrUpdateOneNote(OneNote oneNote) {
		this.getHibernateTemplate().saveOrUpdate(oneNote);
		
	}

	@Override
	public OneNote getOneNote(Long noteId) {
		return this.getHibernateTemplate().get(OneNote.class, noteId);
	}

	@Override
	public void updateOneNote(OneNote updateOneNote) {
		this.getHibernateTemplate().update(updateOneNote);
	}

	@Override
	public void deleteOneNote(OneNote oneNote) {
		oneNote.getNoteBook().getOneNoteSet().remove(oneNote);
		if(oneNote.getNoteFavorite()!=null) {
			oneNote.getNoteFavorite().getOneNoteSet().remove(oneNote);
		}
		oneNote.setNoteFavorite(null);
		this.getHibernateTemplate().delete(oneNote);
	}

}
