package com.web.bo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.web.dao.ShareNoteDao;
import com.web.po.NoteBook;
import com.web.po.NoteUser;
import com.web.po.OneNote;
import com.web.po.ShareNote;

@Transactional
public class ShareNoteBoImpl implements ShareNoteBo {
	private ShareNoteDao shareNoteDao;
	private NoteUserBo noteUserBo;
	private OneNoteBo oneNoteBo;

	public void setShareNoteDao(ShareNoteDao shareNoteDao) {
		this.shareNoteDao = shareNoteDao;
	}

	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}

	public void setOneNoteBo(OneNoteBo oneNoteBo) {
		this.oneNoteBo = oneNoteBo;
	}

	@Override
	public void saveOneShareNote(ShareNote shareNote) {
		shareNoteDao.saveOneShareNote(shareNote);
	}

	@Override
	public List<ShareNote> findAllShareNote() {
		List<ShareNote> shareNoteList = shareNoteDao.findAllShareNote();
		return shareNoteList == null || shareNoteList.size() == 0 ? null : shareNoteList;
	}

	@Override
	public ShareNote getOneShareNone(Long shareId) {
		return shareNoteDao.getOneShareNote(shareId);
	}

	@Override
	public void updateShareNote(ShareNote shareNote) {
		shareNoteDao.updateShareNote(shareNote);

	}

	@Override
	public void deleteOneShareNote(ShareNote shareNote) {
		// updateShareNote(shareNote);
		shareNoteDao.deleteOneShareNote(shareNote);

	}

	@Override
	public OneNote buyOneNote(String payType, NoteUser loginUser, ShareNote shareNote) {
		shareNote.setViewTotal(shareNote.getViewTotal() + 1);
		shareNoteDao.updateShareNote(shareNote);
		boolean hasPay = false;
		Set<ShareNote> shareNoteSet = loginUser.getShareNoteSet();
		Iterator<ShareNote> iterator = shareNoteSet.iterator();
		while (iterator.hasNext()) {
			ShareNote payShareNote = iterator.next();
			if (payShareNote.getShareId().equals(shareNote.getShareId())) {
				hasPay = true;
				break;
			}
		}
		OneNote oneNote = null;
		// 免费或者已购买
		if (shareNote.getCurrency().doubleValue() == 0.0 || hasPay) {
			oneNote = shareNote.getOneNote();
		}
		// 使用云币购买
		if (payType != null && !payType.equals("") && payType.equals("1")) {
			if (shareNote.getCurrency().doubleValue() <= loginUser.getCurrency().doubleValue()) {
				NoteUser authorNoteUser = shareNote.getNoteUser();
				authorNoteUser.setCurrency(authorNoteUser.getCurrency() + shareNote.getCurrency());
				loginUser.setCurrency(loginUser.getCurrency() - shareNote.getCurrency());
				loginUser.getShareNoteSet().add(shareNote);
				noteUserBo.update(loginUser);
				noteUserBo.update(authorNoteUser);
				oneNote = shareNote.getOneNote();
			}
		}
		// 使用VIP特权购买
		if (payType != null && !payType.equals("") && payType.equals("2")) {
			if (loginUser.getPrivilege().longValue()>0) {
				NoteUser authorNoteUser = shareNote.getNoteUser();
				authorNoteUser.setCurrency(authorNoteUser.getCurrency() + shareNote.getCurrency());
				loginUser.setPrivilege(loginUser.getPrivilege().longValue()-1l);
				loginUser.getShareNoteSet().add(shareNote);
				noteUserBo.update(loginUser);
				noteUserBo.update(authorNoteUser);
				oneNote = shareNote.getOneNote();
			}
		}
		return oneNote;
	}

	@Override
	public String cloneOneNote(ShareNote shareNote, NoteUser loginUser) {
		boolean hasPay = false;
		Set<ShareNote> shareNoteSet = loginUser.getShareNoteSet();
		Iterator<ShareNote> iterator = shareNoteSet.iterator();
		while (iterator.hasNext()) {
			ShareNote payShareNote = iterator.next();
			if (payShareNote.getShareId().equals(shareNote.getShareId())) {
				hasPay = true;
				break;
			}
		}

		if (shareNote.getCurrency().doubleValue() == 0.0 || hasPay) {
			OneNote oneNote = new OneNote();
			Iterator<NoteBook> iteratorNoteBook = loginUser.getNoteBookSet().iterator();
			while (iteratorNoteBook.hasNext()) {
				NoteBook noteBook = iteratorNoteBook.next();
				if (noteBook.getNoteType().getTypeId() != 1L) {
					oneNote.setNoteBook(noteBook);
					break;
				}
			}
			if (oneNote.getNoteBook() != null && oneNote.getNoteBook().getNoteType().getTypeId() != 1L) {
				oneNote.setTitle(shareNote.getOneNote().getTitle());
				oneNote.setBody(shareNote.getOneNote().getBody());
				oneNote.setNoteUser(loginUser);
				oneNote.setNoteFavorite(null);
				oneNote.setCreateDate(new Date());
				oneNote.setShareNote(null);
				oneNoteBo.saveOneNote(oneNote);
				return "cloneOk";
			} else {
				return "noNoteBook";

			}
		}
		return "noPay";
	}

}
