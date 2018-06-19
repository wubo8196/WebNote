package com.web.bo;

import java.io.IOException;

import org.hibernate.criterion.DetachedCriteria;

import com.web.po.NoteUser;
import com.web.po.Order;

public interface NoteUserBo {
	
	/**
	 * 提交注册
	 * @param noteUser
	 * @return
	 */
	public NoteUser userRegist(NoteUser noteUser);

	/**
	 * 条件查询单个用户
	 * @param detachedCriteria
	 * @return
	 */
	public NoteUser getOneNoteUserByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 更新操作
	 * @param noteUser
	 */
	public void update(NoteUser noteUser);
    
	/**
	 * 根据id查用户
	 * @param userId
	 * @return
	 */
	public NoteUser getOneNoteUserById(Long userId);

	/**
	 * 充值vip
	 * @param noteUser
	 * @param order
	 */
	public boolean addVipDate(NoteUser noteUser, Order order);

	/**
	 * 发送找回密码邮件
	 * @param noteUser
	 */
	public void toFindPass(NoteUser noteUser) throws IOException;
}
