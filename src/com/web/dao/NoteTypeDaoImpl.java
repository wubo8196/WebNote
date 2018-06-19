package com.web.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.web.po.NoteType;

public class NoteTypeDaoImpl extends HibernateDaoSupport implements NoteTypeDao {

	@Override
	public NoteType getNoteTypeById(Long id) {
		NoteType noteType = this.getHibernateTemplate().get(NoteType.class, id);
		return noteType;
	}

}
