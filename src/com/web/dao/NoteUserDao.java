package com.web.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.web.po.NoteUser;

public interface NoteUserDao {
	
	/**
	 * 保存一个用户
	 * @return
	 */
	public NoteUser saveOneNoteUser(NoteUser noteUser);

	/**
	 * 条件获取用户集合
	 * @param detachedCriteria
	 * @return
	 */
	public List<NoteUser> findNoteUserByCriteria(DetachedCriteria detachedCriteria);

	
	/**
	 * 更新
	 * @param noteUser
	 */
	public void update(NoteUser noteUser);

	/**
	 * 根据id查
	 * @param userId
	 * @return
	 */
	public NoteUser getOneNoteUserById(Long userId);

	/**
	 * 通过邮件地址查
	 * @param userEmail
	 * @return
	 */
	public NoteUser getOneNoteUserByEmail(String userEmail);
}
