package com.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.bo.NoteUserBo;
import com.web.po.NoteUser;
import com.web.utils.VipDateUtil;

public class SystemAction extends ActionSupport {

	private static final long serialVersionUID = 6448615895046129522L;
	private NoteUserBo noteUserBo;
	private String noteBookId;
	private String vip;
	private Long payResult;
	public String getNoteBookId() {
		return noteBookId;
	}
	public void setNoteBookId(String noteBookId) {
		this.noteBookId = noteBookId;
	}
	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String index() {
		
		NoteUser noteUser = (NoteUser) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		NoteUser loginUser = noteUserBo.getOneNoteUserById(noteUser.getUserId());
		ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
		if(loginUser.getVipEndDate().getTime()>VipDateUtil.getEndDate().getTime()) {
			setVip("yes");
		}else {
			setVip("no");
		}
		return "indexPage";
	}
	public Long getPayResult() {
		return payResult;
	}
	public void setPayResult(Long payResult) {
		this.payResult = payResult;
	}
}
