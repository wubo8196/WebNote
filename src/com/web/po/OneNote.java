package com.web.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 笔记实体
 * 
 * @author 吴波
 *
 */
public class OneNote implements Serializable, Comparable<OneNote> {
	private static final long serialVersionUID = 6558081590655554916L;

	/**
	 * 笔记id
	 */
	private Long noteId;

	/**
	 * 关联所有者
	 */
	private NoteUser noteUser;

	/**
	 * 关联笔记本
	 */
	private NoteBook noteBook;

	/**
	 * 关联收藏夹
	 */
	private NoteFavorite noteFavorite;

	/**
	 * 笔记标题
	 */
	private String title;

	/**
	 * 笔记内容
	 */
	private String body;

	/**
	 * 笔记创建时间
	 */
	private Date createDate;
	
	/**
	 * 关联分享
	 */
	private ShareNote shareNote;

	/**
	 * 空参构造
	 */
	public OneNote() {
	}

	/**
	 * 全参构造
	 * 
	 * @param noteId
	 * @param noteUser
	 * @param noteBook
	 * @param noteFavorite
	 * @param title
	 * @param body
	 * @param createDate
	 */
	public OneNote(Long noteId, NoteUser noteUser, NoteBook noteBook, NoteFavorite noteFavorite, String title,
			String body, Date createDate) {
		super();
		this.noteId = noteId;
		this.noteUser = noteUser;
		this.noteBook = noteBook;
		this.noteFavorite = noteFavorite;
		this.title = title;
		this.body = body;
		this.createDate = createDate;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public NoteUser getNoteUser() {
		return noteUser;
	}

	public void setNoteUser(NoteUser noteUser) {
		this.noteUser = noteUser;
	}

	public NoteBook getNoteBook() {
		return noteBook;
	}

	public void setNoteBook(NoteBook noteBook) {
		this.noteBook = noteBook;
	}

	public NoteFavorite getNoteFavorite() {
		return noteFavorite;
	}

	public void setNoteFavorite(NoteFavorite noteFavorite) {
		this.noteFavorite = noteFavorite;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public ShareNote getShareNote() {
		return shareNote;
	}

	public void setShareNote(ShareNote shareNote) {
		this.shareNote = shareNote;
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
		OneNote oneNoteEq = (OneNote) obj;
		if (noteId != oneNoteEq.getNoteId()) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(OneNote o) {
		if (o == null||noteId==null) {
			return 1;
		}
		if (noteId > o.getNoteId()) {
			return -1;
		}
		else if (noteId == o.getNoteId()) {
			return 0;
		}
		return 1;
	}
}
