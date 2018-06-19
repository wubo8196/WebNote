package com.web.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.web.po.ShareNote;

public class ShareNoteDaoImpl extends HibernateDaoSupport implements ShareNoteDao {

	@Override
	public void saveOneShareNote(ShareNote shareNote) {
		this.getHibernateTemplate().saveOrUpdate(shareNote);
		
	}

	@Override
	public List<ShareNote> findAllShareNote() {
		return (List<ShareNote>) this.getHibernateTemplate().find("from ShareNote order by shareId DESC");
	}

	@Override
	public ShareNote getOneShareNote(Long shareId) {
		return this.getHibernateTemplate().get(ShareNote.class, shareId);
	}

	@Override
	public void updateShareNote(ShareNote shareNote) {
		this.getHibernateTemplate().update(shareNote);
		
	}

	@Override
	public void deleteOneShareNote(ShareNote shareNote) {
		removeBayShareNote(shareNote);
		this.getHibernateTemplate().delete(shareNote);
		
	}

	@Override
	public void removeBayShareNote(ShareNote shareNote) {
		String sql = "delete from NoteUser_shareNoteSet where elt ="+shareNote.getShareId()+"";
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

}
