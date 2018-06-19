package com.web.bo;

import com.web.po.NoteBook;
import com.web.po.NoteUser;

public interface NoteBookBo {

	/**
	 * 保存一个笔记本
	 * @param noteBook
	 */
	void saveOneNoteBook(NoteBook noteBook);

	/**
	 * 通过id获取笔记本
	 * @param bookId
	 * @return
	 */
	NoteBook getNoteBookById(Long bookId);

	/**
	 * 获取某用户的回收站
	 * @param noteUser
	 * @return
	 */
	NoteBook getRecycleByUser(NoteUser noteUser);

	/**
	 * 更新笔记本
	 * @param updateNoteBook
	 */
	void updateOneNoteBook(NoteBook updateNoteBook);

	/**
	 * 删除笔记本
	 * @param deleteNoteBook
	 */
	void deleteNoteBook(NoteBook deleteNoteBook);

}
