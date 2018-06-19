package com.web.dao;

import com.web.po.NoteType;

public interface NoteTypeDao {

	/**
	 * 通过ID获取笔记本类型
	 * @param id
	 * @return
	 */
	NoteType getNoteTypeById(Long id);

}
