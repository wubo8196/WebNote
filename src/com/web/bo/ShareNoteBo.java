package com.web.bo;

import java.util.List;

import com.web.po.NoteUser;
import com.web.po.OneNote;
import com.web.po.ShareNote;

public interface ShareNoteBo {

	/**
	 * 保存一条分享
	 * @param shareNote
	 */
	void saveOneShareNote(ShareNote shareNote);

	/**
	 * 获取所有分享
	 * @return
	 */
	List<ShareNote> findAllShareNote();

	/**
	 * 通过id获得分享实例
	 * @param shareId
	 * @return
	 */
	ShareNote getOneShareNone(Long shareId);

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
	 * 购买一个笔记
	 * @param payType
	 * @param loginUser
	 * @param shareNote 
	 * @return
	 */
	OneNote buyOneNote(String payType, NoteUser loginUser, ShareNote shareNote);

	/**
	 * 克隆一个笔记
	 * @param shareNote
	 * @param loginUser
	 * @return
	 */
	String cloneOneNote(ShareNote shareNote, NoteUser loginUser);

}
