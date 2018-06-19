package com.web.bo;

import com.web.po.NoteType;

public interface NoteTypeBo {

	/**
	 * 通过ID获取笔记本类型
	 * @param l
	 * @return
	 */
	NoteType getNoteTypeById(Long id);

}
