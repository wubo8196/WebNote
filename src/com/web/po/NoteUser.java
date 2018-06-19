package com.web.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 * 
 * @author 吴波
 */
public class NoteUser implements Serializable {
	private static final long serialVersionUID = -1904870695814800269L;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 昵称
	 */
	private String userName;
	
	/**
	 * 用户电子邮箱
	 */
	private String userEmail;
	/**
	 * 用户密码
	 */
	private String userPassword;
	
	/**
	 * 邮箱验证码
	 */
	private String token;
	
	/**
	 * 是否启用 0禁用 1启用
	 */
	private String state;

	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 会员结束时间
	 */
	private Date vipEndDate;
	
	/**
	 * 剩余特权下载次数
	 */
	private Long privilege;
	
	/**
	 * 账户余额
	 */
	private Double currency;

	/**
	 * 用户说明
	 */
	private String userDesc;
	
	/**
	 * 头像
	 */
	private String userIcon;
	
	/**
	 * 关联多个笔记本
	 */
	private Set<NoteBook> noteBookSet = new HashSet<>();
	
	/**
	 * 购买的分享
	 */
	private Set<ShareNote> shareNoteSet = new HashSet<>();
	
	/**
	 * 关联收藏夹
	 */
	private NoteFavorite noteFavorite;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getCurrency() {
		return currency;
	}

	public void setCurrency(Double currency) {
		this.currency = currency;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Set<NoteBook> getNoteBookSet() {
		return noteBookSet;
	}

	public void setNoteBookSet(Set<NoteBook> noteBookSet) {
		this.noteBookSet = noteBookSet;
	}

	public NoteFavorite getNoteFavorite() {
		return noteFavorite;
	}

	public void setNoteFavorite(NoteFavorite noteFavorite) {
		this.noteFavorite = noteFavorite;
	}
	
	public Set<ShareNote> getShareNoteSet() {
		return shareNoteSet;
	}

	public void setShareNoteSet(Set<ShareNote> shareNoteSet) {
		this.shareNoteSet = shareNoteSet;
	}

	
	public Long getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Long privilege) {
		this.privilege = privilege;
	}

	public Date getVipEndDate() {
		return vipEndDate;
	}

	public void setVipEndDate(Date vipEndDate) {
		this.vipEndDate = vipEndDate;
	}

}
