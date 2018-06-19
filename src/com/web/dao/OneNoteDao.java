package com.web.dao;

import com.web.po.OneNote;

public interface OneNoteDao {
	/**
	 * 保存一个笔记
	 * @param oneNote
	 */
	void saveOneNote(OneNote oneNote);

	/**
	 * 保存或更新
	 * @param oneNote
	 */
	void saveOrUpdateOneNote(OneNote oneNote);

	/**
	 * 获得一个笔记
	 * @param noteId
	 * @return
	 */
	OneNote getOneNote(Long noteId);

	/**
	 * 更新一个笔记
	 * @param updateOneNote
	 */
	void updateOneNote(OneNote updateOneNote);

	/**
	 * 删除一个笔记
	 * @param oneNote
	 */
	void deleteOneNote(OneNote oneNote);

}
