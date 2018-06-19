package com.web.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.web.po.NoteUser;

public class NoteUserDaoImpl extends HibernateDaoSupport implements NoteUserDao {

	@Override
	public NoteUser saveOneNoteUser(NoteUser noteUser) {
		this.getHibernateTemplate().save(noteUser);
		return noteUser;
	}

	@Override
	public List<NoteUser> findNoteUserByCriteria(DetachedCriteria detachedCriteria) {
		List<NoteUser> noteUserList = (List<NoteUser>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return noteUserList;
	}

	@Override
	public void update(NoteUser noteUser) {
		this.getHibernateTemplate().update(noteUser);
		
	}

	@Override
	public NoteUser getOneNoteUserById(Long userId) {
		NoteUser noyeUser = this.getHibernateTemplate().get(NoteUser.class, userId);
		return noyeUser;
	}

	@Override
	public NoteUser getOneNoteUserByEmail(String userEmail) {
		String hql = "from NoteUser where userEmail = ?";
		List<NoteUser> noteUserList = (List<NoteUser>) this.getHibernateTemplate().find(hql, userEmail);
		return noteUserList!=null&&noteUserList.size()>0?noteUserList.get(0):null;
	}

}
