package com.web.bo;

import com.web.po.NoteUser;
import com.web.po.OneNote;

public interface OneNoteBo {

	/**
	 * 保存一个笔记
	 * @param oneNote
	 */
	void saveOneNote(OneNote oneNote);

	/**
	 * 获得一个笔记
	 * @param noteId
	 * @return
	 */
	OneNote getOneNote(Long noteId);

	/**
	 * 更新一个笔记
	 * @param saveOneNote
	 */
	void updateOneNote(OneNote updateOneNote);

	/**
	 * 删除一个笔记 在回收站彻底删除 不在回收站移到回收站
	 * @param noteId
	 * @param noteUser 
	 */
	void deleteOneNote(Long noteId, NoteUser noteUser);

}
