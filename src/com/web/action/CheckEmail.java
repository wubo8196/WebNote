package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.web.bo.NoteUserBo;
import com.web.po.NoteUser;

/**
 * 邮箱验证Action
 * 
 * @author 吴波
 *
 */
public class CheckEmail extends ActionSupport {

	private static final long serialVersionUID = 3883669201051207149L;
	final Logger logger = Logger.getLogger(CheckEmail.class);
	private NoteUserBo noteUserBo;
	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}

	private String emailAddr;

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String checkEmailAddr() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(NoteUser.class);
		detachedCriteria.add(Restrictions.eq("userEmail",emailAddr ));
		detachedCriteria.add(Restrictions.eq("state", "1"));
		NoteUser noteUser = noteUserBo.getOneNoteUserByCriteria(detachedCriteria);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			if(noteUser==null) {
				printWriter.println("ok");
			}else {
				printWriter.print("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("提交邮箱地址:" + emailAddr);
		return NONE;
	}
}
