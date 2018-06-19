package com.web.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.bo.NoteBookBo;
import com.web.bo.NoteTypeBo;
import com.web.po.NoteBook;
import com.web.po.NoteUser;

public class NoteBookAction extends ActionSupport implements ModelDriven<NoteBook> {

	private static final long serialVersionUID = -716203667524281241L;
	final Logger logger = Logger.getLogger(NoteBookAction.class);
	private NoteBookBo noteBookBo;
	private NoteTypeBo noteTypeBo;

	public void setNoteBookBo(NoteBookBo noteBookBo) {
		this.noteBookBo = noteBookBo;
	}

	public void setNoteTypeBo(NoteTypeBo noteTypeBo) {
		this.noteTypeBo = noteTypeBo;
	}

	private NoteBook noteBook = new NoteBook();

	@Override
	public NoteBook getModel() {
		return noteBook;
	}
	
	public NoteBook getNoteBook() {
		return noteBook;
	}

	/**
	 * 保存一个笔记本
	 * 
	 * @return
	 */
	public String saveOneNoteBook() {
		logger.info("保存一个笔记本");
		NoteBook saveNoteBook = new NoteBook();
		saveNoteBook.setNoteUser(getLoginUser());
		saveNoteBook.setNoteType(noteTypeBo.getNoteTypeById(2L));
		saveNoteBook.setBookName(noteBook.getBookName());
		saveNoteBook.setBookDesc(noteBook.getBookDesc());
		saveNoteBook.setCreateDate(new Date());
		noteBookBo.saveOneNoteBook(saveNoteBook);
		return NONE;
	}
	
	/**跳转到笔记本编辑页面
	 * @return
	 */
	public String toUpdateNoteBook() {
		logger.info("取得要编辑的笔记本");
		noteBook = noteBookBo.getNoteBookById(noteBook.getBookId());
		return "updateNoteBookPage";
		
	}
	
	/**
	 * 更新一个笔记本
	 * @return
	 */
	public String updateOneNoteBook() {
		NoteBook updateNoteBook = noteBookBo.getNoteBookById(noteBook.getBookId());
		updateNoteBook.setBookName(noteBook.getBookName());
		updateNoteBook.setBookDesc(noteBook.getBookDesc());
		noteBookBo.updateOneNoteBook(updateNoteBook);
		return NONE;
	}
	
	/**
	 * 检查笔记本是否为空
	 * @return
	 */
	public String checkNoteBook() {
		NoteBook checkNoteBook = noteBookBo.getNoteBookById(this.noteBook.getBookId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(checkNoteBook.getOneNoteSet()==null||checkNoteBook.getOneNoteSet().size()==0) {
				out.write("isNull");
			}else {
				out.write("notNull");
			}
		} catch (Exception e) {
			out.write("error");
		}
		return NONE;
	}
	
	/**
	 * 删除一个笔记本
	 * @return
	 */
	public String deleteOneNoteBook() {
		NoteBook deleteNoteBook = noteBookBo.getNoteBookById(this.noteBook.getBookId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			noteBookBo.deleteNoteBook(deleteNoteBook);
			out.write("deleteOk");
			
		} catch (Exception e) {
			out.write("error");
		}
		return NONE;
	}
	
	/**
	 * 获得登录用户
	 * @return
	 */
	private NoteUser getLoginUser() {
		NoteUser noteUser = (NoteUser) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		logger.info("获取登录用户:"+noteUser.getUserName());
		return noteUser;
	}
	
}
