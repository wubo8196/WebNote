package com.web.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.bo.NoteBookBo;
import com.web.bo.NoteUserBo;
import com.web.bo.OneNoteBo;
import com.web.po.NoteBook;
import com.web.po.NoteUser;
import com.web.po.OneNote;

public class OneNoteAction extends ActionSupport {

	private static final long serialVersionUID = -3792054952968082117L;
	final Logger logger = Logger.getLogger(OneNoteAction.class);
	private NoteBookBo noteBookBo;
	private OneNoteBo oneNoteBo;
	private NoteUserBo noteUserBo;
	private NoteBook noteBook;
	private Set<OneNote> oneNoteS;
	private String searchNoteType;
	private OneNote oneNote;

	public NoteBook getNoteBook() {
		return noteBook;
	}

	public void setNoteBook(NoteBook noteBook) {
		this.noteBook = noteBook;
	}

	public void setNoteBookBo(NoteBookBo noteBookBo) {
		this.noteBookBo = noteBookBo;
	}

	public void setOneNoteBo(OneNoteBo oneNoteBo) {
		this.oneNoteBo = oneNoteBo;
	}

	public Set<OneNote> getOneNoteS() {
		return oneNoteS;
	}

	public void setOneNoteS(Set<OneNote> oneNoteS) {
		this.oneNoteS = oneNoteS;
	}

	public String getSearchNoteType() {
		return searchNoteType;
	}

	public void setSearchNoteType(String searchNoteType) {
		this.searchNoteType = searchNoteType;
	}

	public void setOneNote(OneNote oneNote) {
		this.oneNote = oneNote;
	}

	public OneNote getOneNote() {
		return oneNote;
	}
	
	public NoteUserBo getNoteUserBo() {
		return noteUserBo;
	}

	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}

	/**
	 * 保存一个笔记
	 * 
	 * @return
	 */
	public String saveOneNote() {
		NoteUser noteUser = getLoginUser();
		oneNote.setNoteUser(noteUser);
		NoteBook noteBook = noteBookBo.getNoteBookById(oneNote.getNoteBook().getBookId());
		oneNote.setNoteBook(noteBook);
		// 默认不放收藏夹
		oneNote.setNoteFavorite(null);
		oneNote.setCreateDate(new Date());
		oneNoteBo.saveOneNote(oneNote);
		return NONE;
	}
	
	/**
	 * 从回收站中还原
	 * @return
	 */
	public String restoreOneNote() {
		NoteUser noteUser = getLoginUser();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			oneNote = oneNoteBo.getOneNote(oneNote.getNoteId());
			Iterator<NoteBook> iterator = noteUser.getNoteBookSet().iterator();
			while(iterator.hasNext()) {
				NoteBook noteBook = iterator.next();
				if(noteBook.getNoteType().getTypeId()!=1L) {
					oneNote.setNoteBook(noteBook);
					break;
				}
			}
			if(oneNote.getNoteBook().getNoteType().getTypeId()!=1L) {
				oneNoteBo.updateOneNote(oneNote);
				out.write("restoreOk");
			}else {
				throw new Exception("没有笔记本请先创建笔记本");
			}
		} catch (Exception e) {
			out.write("error:"+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取笔记集合
	 * 
	 * @return
	 */
	public String findNoteList() {
		// 查找笔记本或回收站
		if (searchNoteType != null && !"".equals(searchNoteType) && !searchNoteType.equals("F")
				&& !searchNoteType.equalsIgnoreCase("S")) {
			noteBook = noteBookBo.getNoteBookById(oneNote.getNoteBook().getBookId());
			// 含搜索条件
			if (oneNote!=null&&oneNote.getTitle() != null && !"".equals(oneNote.getTitle())) {
				this.setOneNoteS(new TreeSet<>());
				for (OneNote soneNote : noteBook.getOneNoteSet()) {
					if (soneNote.getTitle().contains(oneNote.getTitle())) {
						this.getOneNoteS().add(soneNote);
					}
				}
			} else {
				setOneNoteS(noteBook.getOneNoteSet());
			}
		} // 查找收藏夹
		else if (searchNoteType != null && !"".equals(searchNoteType) && searchNoteType.equals("F")) {
			// 含搜索条件
			if (oneNote!=null&&oneNote.getTitle() != null && !"".equals(oneNote.getTitle())) {
				this.setOneNoteS(new TreeSet<>());
				for (OneNote soneNote : getLoginUser().getNoteFavorite().getOneNoteSet()) {
					if (soneNote.getTitle().contains(oneNote.getTitle())) {
						this.getOneNoteS().add(soneNote);
					}
				}
			} else {
				setOneNoteS(getLoginUser().getNoteFavorite().getOneNoteSet());
			}
		} // 查找分享
		else if (searchNoteType != null && !"".equals(searchNoteType) && searchNoteType.equals("S")) {
		}

		return "oneNoteListPage";
	}

	/**
	 * 获取笔记详情
	 * 
	 * @return
	 */
	public String oneNoteDetail() {
		
		oneNote = oneNoteBo.getOneNote(oneNote.getNoteId());
		NoteUser oneNotenoteUser = oneNote.getNoteUser();
		NoteUser loginUser = getLoginUser();
		if(oneNotenoteUser.getUserId().longValue()!=loginUser.getUserId().longValue()) {
			return "indexAction";
		}
		return "oneNoteDetail";
	}

	/**
	 * 获取要修改的笔记
	 * 
	 * @return
	 */
	public String editOneNotePre() {
		oneNote = oneNoteBo.getOneNote(oneNote.getNoteId());
		return "editOneNote";
	}

	/**
	 * 提交修改的笔记
	 * 
	 * @return
	 */
	public String editSubmitOneNote() {
		OneNote updateOneNote = oneNoteBo.getOneNote(oneNote.getNoteId());
		updateOneNote.setNoteUser(getLoginUser());
		updateOneNote.setNoteBook(noteBookBo.getNoteBookById(oneNote.getNoteBook().getBookId()));
		updateOneNote.setTitle(oneNote.getTitle());
		updateOneNote.setBody(oneNote.getBody());
		oneNoteBo.updateOneNote(updateOneNote);
		return NONE;
	}

	/**
	 * 收藏或取消收藏
	 * 
	 * @return
	 */
	public String likeOrCancle() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			oneNote = oneNoteBo.getOneNote(oneNote.getNoteId());
			if (oneNote.getNoteFavorite() == null) {
				oneNote.setNoteFavorite(getLoginUser().getNoteFavorite());
				out.write("likeOk");
			} else {
				oneNote.setNoteFavorite(null);
				out.write("cancleOk");
			}
			oneNoteBo.updateOneNote(oneNote);
		} catch (Exception e) {
			out.write("error");
		}
		return NONE;
	}
	
	/**
	 * 删除一个笔记
	 * @return
	 */
	public String deleteOneNote() {
		NoteUser noteUser = getLoginUser();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			//从收藏夹中移除
			oneNote = oneNoteBo.getOneNote(oneNote.getNoteId());
			oneNote.setNoteFavorite(null);
			oneNoteBo.deleteOneNote(oneNote.getNoteId(),noteUser);
			out.write("deleteOk");
			
		} catch (Exception e) {
			out.write("error");
		}
		return NONE;
	}
	
	/**
	 * 获得登录用户
	 * 
	 * @return
	 */
	private NoteUser getLoginUser() {
		NoteUser noteUser = noteUserBo.getOneNoteUserById(((NoteUser) ServletActionContext.getRequest().getSession().getAttribute("loginUser")).getUserId());
		logger.info("获取登录用户:" + noteUser.getUserName());
		return noteUser;
	}

}
