package com.web.dao;

import com.web.po.NoteBook;
import com.web.po.NoteUser;

public interface NoteBookDao {

	/**
	 * 保存一个笔记本
	 * @param noteBook
	 */
	void saveOneNoteBook(NoteBook noteBook);

	/**
	 * 通过id获取一个笔记本
	 * @param bookId
	 * @return
	 */
	NoteBook getNoteBookById(Long bookId);

	NoteBook getRecycleByUser(NoteUser noteUser);

	void updateOneNoteBook(NoteBook updateNoteBook);

	/**
	 * 删除一个笔记本
	 * @param deleteNoteBook
	 */
	void deleteNoteBook(NoteBook deleteNoteBook);

}
