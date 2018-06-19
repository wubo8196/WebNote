package com.web.dao;

import java.util.List;

import com.web.po.ShareNote;

public interface ShareNoteDao {

	/**
	 * 保存一个分享
	 * @param shareNote
	 */
	void saveOneShareNote(ShareNote shareNote);

	/**
	 * 获取所有分享
	 * @return
	 */
	List<ShareNote> findAllShareNote();

	/**
	 * 通过id获取一个分享
	 * @param shareId
	 * @return
	 */
	ShareNote getOneShareNote(Long shareId);

	/**
	 * 更新分享
	 * @param shareNote
	 */
	void updateShareNote(ShareNote shareNote);

	/**
	 * 删除一个分享
	 * @param shareNote
	 */
	void deleteOneShareNote(ShareNote shareNote);

	/**
	 * 删除购买次分享的记录
	 * @param shareNote
	 */
	void removeBayShareNote(ShareNote shareNote);

}
