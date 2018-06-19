package com.web.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享笔记实体
 * 
 * @author 吴波
 *
 */
public class ShareNote implements Serializable {

	private static final long serialVersionUID = -6114458413013994015L;

	/**
	 * id
	 */
	private Long shareId;
	
	/**
	 * 分享的标题
	 */
	private String shareTitle;
	
	/**
	 * 分享的时间
	 */
	private Date shareDate;
	
	/**
	 * 分享的用户
	 */
	private NoteUser noteUser;
	
	/**
	 * 分享的笔记
	 */
	private OneNote oneNote;
	
	/**
	 * 需要云币数量 为0表示免费
	 */
	private Double currency;

	/**
	 * 阅读次数
	 */
	private Integer viewTotal;

	/**
	 * 空参构造
	 */
	public ShareNote() {
	}


	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public OneNote getOneNote() {
		return oneNote;
	}

	public void setOneNote(OneNote oneNote) {
		this.oneNote = oneNote;
	}

	public Double getCurrency() {
		return currency;
	}

	public void setCurrency(Double currency) {
		this.currency = currency;
	}

	public Integer getViewTotal() {
		return viewTotal;
	}

	public void setViewTotal(Integer viewTotal) {
		this.viewTotal = viewTotal;
	}


	public NoteUser getNoteUser() {
		return noteUser;
	}


	public void setNoteUser(NoteUser noteUser) {
		this.noteUser = noteUser;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ShareNote shareNote = (ShareNote) obj;
		if (shareId.longValue() != shareNote.getShareId().longValue()) {
			return false;
		}
		return true;
	}

}
