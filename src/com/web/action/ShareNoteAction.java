package com.web.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.bo.NoteUserBo;
import com.web.bo.OneNoteBo;
import com.web.bo.ShareNoteBo;
import com.web.po.NoteUser;
import com.web.po.OneNote;
import com.web.po.ShareNote;

/**
 * 
 * @author 吴波
 *
 */
public class ShareNoteAction extends ActionSupport {

	private static final long serialVersionUID = -8515838971650297394L;
	final Logger logger = Logger.getLogger(ShareNoteAction.class);
	private OneNoteBo oneNoteBo;
	private NoteUserBo noteUserBo;
	private ShareNoteBo shareNoteBo;
	private ShareNote shareNote;
	private OneNote oneNote;
	//购买方式 0 免费 1云币 2会员特权
	private String payType;
	private List<ShareNote> shareNoteList;
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public List<ShareNote> getShareNoteList() {
		return shareNoteList;
	}

	public void setShareNoteList(List<ShareNote> shareNoteList) {
		this.shareNoteList = shareNoteList;
	}

	public void setShareNoteBo(ShareNoteBo shareNoteBo) {
		this.shareNoteBo = shareNoteBo;
	}

	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}

	public void setOneNoteBo(OneNoteBo oneNoteBo) {
		this.oneNoteBo = oneNoteBo;
	}

	public void setOneNote(OneNote oneNote) {
		this.oneNote = oneNote;
	}

	public OneNote getOneNote() {
		return oneNote;
	}

	public ShareNote getShareNote() {
		return shareNote;
	}

	public void setShareNote(ShareNote shareNote) {
		this.shareNote = shareNote;
	}

	/**
	 * 分享准备
	 * 
	 * @return
	 */
	public String shareOneNotePre() {
		oneNote = oneNoteBo.getOneNote(oneNote.getNoteId());
		return "shareOneNote";
	}

	/**
	 * 提交分享
	 * 
	 * @return
	 */
	public String shareNoteSubmit() {
		NoteUser noteUser = getLoginUser();
		shareNote.setShareDate(new Date());
		shareNote.setNoteUser(noteUser);
		OneNote oneNote = oneNoteBo.getOneNote(shareNote.getOneNote().getNoteId());
		shareNote.setOneNote(oneNote);
		shareNote.setViewTotal(0);
		shareNote.setShareId(oneNote.getNoteId());
		shareNoteBo.saveOneShareNote(shareNote);
		noteUser.getShareNoteSet().add(shareNote);
		noteUserBo.update(getLoginUser());
		return NONE;
	}
	
	/**
	 * 取消分享
	 * @return
	 */
	public String cancleShareOneNote() {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			OneNote oneNote = oneNoteBo.getOneNote(this.oneNote.getNoteId());
			ShareNote shareNote = oneNote.getShareNote();
			shareNote.setNoteUser(null);
			shareNote.setOneNote(null);
			getLoginUser().getShareNoteSet().remove(shareNote);
			shareNoteBo.deleteOneShareNote(shareNote);
			out.write("cancleShareOk");
		} catch (Exception e) {
			out.write("error");
		}
		return NONE;
	}

	/**
	 * 获取所有分享
	 * 
	 * @return
	 */
	public String findAllShareNote() {
		shareNoteList = shareNoteBo.findAllShareNote();
		return "allShare";
	}

	/**
	 * 检查分享的笔记是否免费或付费以及用户会员状态
	 * 
	 * @return
	 */
	public String checkShareNote() {
		ShareNote shareNote = shareNoteBo.getOneShareNone(this.shareNote.getShareId());
		NoteUser noteUser = getLoginUser();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String result = "";
		if (shareNote.getCurrency() != null && shareNote.getCurrency().doubleValue() == 0) {
			result += "{\"type\":\"free\"";
		} else {
			result += "{\"type\":\"purchase\"";
		}
		result += ",\"currency\":\"" + shareNote.getCurrency() + "\"";
		result += ",\"privilege\":\"" + noteUser.getPrivilege() + "\"";
		if (noteUser.getVipEndDate().getTime() > new Date().getTime()) {
			result += ",\"vip\":\"yes\"";
		} else {
			result += ",\"vip\":\"no\"";
		}
		boolean hasPay = false;
		Set<ShareNote> shareNoteSet = noteUser.getShareNoteSet();
		Iterator<ShareNote> iterator = shareNoteSet.iterator();
		while (iterator.hasNext()) {
			ShareNote payShareNote = iterator.next();
			if (payShareNote.getShareId().equals(shareNote.getShareId())) {
				hasPay = true;
				break;
			}
		}
		if (hasPay) {
			result += ",\"hasPay\":\"yes\"";
		} else {
			result += ",\"hasPay\":\"no\"";
		}
		result += ",\"userCurrency\":\"" + noteUser.getCurrency() + "\"}";
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			out.write("error");
		}

		return NONE;
	}
	
	/**
	 * 访问笔记
	 * @return
	 */
	public String toOneNote(){
		ShareNote shareNote = shareNoteBo.getOneShareNone(this.shareNote.getShareId());
		oneNote = shareNoteBo.buyOneNote(payType,getLoginUser(),shareNote);
		return "shareNoteDetail";
	}
	
	/**
	 * 克隆笔记
	 * @return
	 */
	public String cloneOneNote() {
		ShareNote shareNote = shareNoteBo.getOneShareNone(this.shareNote.getShareId());
		String result =null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			result= shareNoteBo.cloneOneNote(shareNote,getLoginUser());
			out.write(result);
		} catch (Exception e) {
			out.write("error"+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获得登录用户
	 * 
	 * @return
	 */
	private NoteUser getLoginUser() {
		NoteUser noteUser = noteUserBo.getOneNoteUserById(
				((NoteUser) ServletActionContext.getRequest().getSession().getAttribute("loginUser")).getUserId());
		logger.info("获取登录用户:" + noteUser.getUserName());
		return noteUser;
	}

}
