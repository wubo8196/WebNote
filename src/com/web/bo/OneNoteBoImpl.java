package com.web.bo;

import org.springframework.transaction.annotation.Transactional;

import com.web.dao.OneNoteDao;
import com.web.po.NoteBook;
import com.web.po.NoteUser;
import com.web.po.OneNote;
import com.web.po.ShareNote;

@Transactional
public class OneNoteBoImpl implements OneNoteBo {
	private OneNoteDao oneNoteDao;
	private NoteBookBo noteBookBo;
	private ShareNoteBo shareNotebo;
	private NoteUserBo noteUserBo;
	public void setOneNoteDao(OneNoteDao oneNoteDao) {
		this.oneNoteDao = oneNoteDao;
	}
	public void setNoteBookBo(NoteBookBo noteBookBo) {
		this.noteBookBo = noteBookBo;
	}
	public void setShareNotebo(ShareNoteBo shareNotebo) {
		this.shareNotebo = shareNotebo;
	}
	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}

	@Override
	public void saveOneNote(OneNote oneNote) {
		oneNoteDao.saveOneNote(oneNote);
	}

	@Override
	public OneNote getOneNote(Long noteId) {
		return oneNoteDao.getOneNote(noteId);
	}

	@Override
	public void updateOneNote(OneNote updateOneNote) {
		oneNoteDao.updateOneNote(updateOneNote);
	}

	@Override
	public void deleteOneNote(Long noteId,NoteUser noteUser) {
		OneNote oneNote = oneNoteDao.getOneNote(noteId);
		if(oneNote.getNoteBook().getNoteType().getTypeId().equals(1L)) { 
			ShareNote shareNote = oneNote.getShareNote();
			if(shareNote!=null) {
				shareNote.setNoteUser(null);
				shareNote.setOneNote(null);
				shareNotebo.deleteOneShareNote(shareNote);
				noteUser.getShareNoteSet().remove(shareNote);
			}
			oneNoteDao.deleteOneNote(oneNote);
		}else if(!oneNote.getNoteBook().getNoteType().getTypeId().equals(1L)) {
			//获取该用户的回收站
			NoteBook noteBook = noteBookBo.getRecycleByUser(oneNote.getNoteUser());
			oneNote.setNoteBook(noteBook);
			ShareNote shareNote = oneNote.getShareNote();
			if(shareNote!=null) {
				shareNote.setNoteUser(null);
				shareNote.setOneNote(null);
				noteUser.getShareNoteSet().remove(shareNote);
				shareNotebo.deleteOneShareNote(shareNote);
			}
			oneNoteDao.updateOneNote(oneNote);
		}
		
	}

}
