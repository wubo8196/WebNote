package com.web.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 笔记本实体
 * 
 * @author 吴波
 *
 */
public class NoteBook implements Serializable {

	private static final long serialVersionUID = 4643776493136533647L;

	/**
	 * 笔记本id
	 */
	private Long bookId;

	/**
	 * 关联用户
	 */
	private NoteUser noteUser;

	/**
	 * 关联笔记本类型
	 */
	private NoteType noteType;

	/**
	 * 关联笔记
	 */
	private Set<OneNote> oneNoteSet = new TreeSet<>();

	/**
	 * 笔记本名
	 */
	private String bookName;

	/**
	 * 笔记本说明
	 */
	private String bookDesc;

	/**
	 * 创建日期
	 */
	private Date createDate;

	/**
	 * 空参构造
	 */
	public NoteBook() {
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public NoteUser getNoteUser() {
		return noteUser;
	}

	public void setNoteUser(NoteUser noteUser) {
		this.noteUser = noteUser;
	}

	public NoteType getNoteType() {
		return noteType;
	}

	public void setNoteType(NoteType noteType) {
		this.noteType = noteType;
	}

	public Set<OneNote> getOneNoteSet() {
		return oneNoteSet;
	}

	public void setOneNoteSet(Set<OneNote> oneNoteSet) {
		this.oneNoteSet = oneNoteSet;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

}
