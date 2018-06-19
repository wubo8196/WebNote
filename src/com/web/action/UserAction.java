package com.web.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.bo.NoteUserBo;
import com.web.po.NoteBook;
import com.web.po.NoteFavorite;
import com.web.po.NoteType;
import com.web.po.NoteUser;
import com.web.po.OneNote;
import com.web.utils.MD5Utils;
import com.web.utils.MailUtil;
import com.web.utils.ReadModel;
import com.web.utils.VipDateUtil;

public class UserAction extends ActionSupport implements ModelDriven<NoteUser> {

	private static final long serialVersionUID = -8629892132553041174L;
	final Logger logger = Logger.getLogger(UserAction.class);
	// 注入业务层
	private NoteUserBo noteUserBo;

	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}

	private NoteUser noteUser = new NoteUser();

	@Override
	public NoteUser getModel() {
		return noteUser;
	}

	/**
	 * 提交注册
	 * 
	 * @return
	 * @throws IOException
	 */
	public String registSubmit() throws IOException {
		logger.info(noteUser);
		// 生成邮箱验证码
		String token = UUID.randomUUID().toString();
		noteUser.setToken(token);
		noteUser.setState("0");
		noteUser.setCreateDate(new Date());
		noteUser.setVipEndDate(VipDateUtil.getStartDate());
		noteUser.setPrivilege(0L);
		//新用户送10云币
		noteUser.setCurrency(new Double(10L));
		noteUser.setUserPassword(MD5Utils.md5(noteUser.getUserPassword()));
		logger.info("初始化一个笔记");
		OneNote oneNote = new OneNote();
		oneNote.setNoteUser(noteUser);
		oneNote.setTitle("欢迎使用云笔记");
		oneNote.setBody("欢迎来到您的笔记，您可以分享此笔记");
		oneNote.setCreateDate(new Date());
		logger.info("初始化两个默认笔记本");
		NoteBook noteBook1 = new NoteBook();
		NoteBook noteBook2 = new NoteBook();
		NoteType noteType1 = new NoteType();
		NoteType noteType2 = new NoteType();
		noteType1.setTypeId(1L);
		noteType1.setTypeName("回收站");
		noteType2.setTypeId(2L);
		noteType2.setTypeName("默认笔记本");
		noteBook1.setNoteType(noteType1);
		noteBook2.setNoteType(noteType2);
		noteBook1.setBookName("回收站");
		noteBook2.setBookName("默认笔记本");
		noteBook2.getOneNoteSet().add(oneNote);
		noteBook1.setCreateDate(new Date());
		noteBook2.setCreateDate(new Date());
		noteUser.getNoteBookSet().add(noteBook1);
		noteUser.getNoteBookSet().add(noteBook2);
		logger.info("初始化收藏夹");
		NoteFavorite noteFavorite = new NoteFavorite();
		noteFavorite.getOneNoteSet().add(oneNote);
		noteUser.setNoteFavorite(noteFavorite);
		noteUserBo.userRegist(noteUser);
		String html = ReadModel.readModel("mailModel/accountActivate.html");
		html = html.replace("{userName}", noteUser.getUserName());
		InetAddress addr = InetAddress.getLocalHost();
		html = html.replace("{activateUrl}",
				"http://106.14.124.85" + ":" + ServletActionContext.getRequest().getLocalPort()
						+ ServletActionContext.getRequest().getContextPath() + "/user_activate.action?userId="
						+ noteUser.getUserId() + "&" + "token=" + noteUser.getToken());
		MailUtil.sendEmil(noteUser.getUserEmail(), "账户激活", html);
		return "registEnd";
	}
	
	/**
	 * 更新用户信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String updateSubmit() throws UnsupportedEncodingException {
		NoteUser updateNoteUser = getLoginUser();
		updateNoteUser.setUserName(noteUser.getUserName());
		updateNoteUser.setUserIcon(noteUser.getUserIcon());
		updateNoteUser.setUserDesc(noteUser.getUserDesc());
		noteUserBo.update(updateNoteUser);
		return NONE;
	}

	/**
	 * 邮箱激活
	 * 
	 * @return
	 */
	public String activate() {
		NoteUser saveNoteUser = noteUserBo.getOneNoteUserById(noteUser.getUserId());
		if (saveNoteUser != null && saveNoteUser.getToken().equals(noteUser.getToken())) {
			saveNoteUser.setState("1");
		}
		saveNoteUser.setToken(UUID.randomUUID().toString());
		noteUserBo.update(saveNoteUser);
		return LOGIN;
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login() {
		DetachedCriteria criteria = DetachedCriteria.forClass(NoteUser.class);
		if (noteUser != null) {
			logger.info("用户登录，邮箱地址：" + noteUser.getUserEmail());
			criteria.add(Restrictions.eq("userEmail", noteUser.getUserEmail()));
			criteria.add(Restrictions.eq("userPassword", MD5Utils.md5(noteUser.getUserPassword())));
			criteria.add(Restrictions.eq("state", "1"));
		}
		NoteUser loginUser = noteUserBo.getOneNoteUserByCriteria(criteria);
		if (loginUser == null) {
			return LOGIN;
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
			return "indexAction";
		}
	}
	
	/**
	 * 找回密码
	 * @return
	 * @throws IOException 
	 */
	public String findPass() throws IOException {
		noteUserBo.toFindPass(noteUser);
		return "toFindPass";
	}
	
	/**
	 * 重置密码页面
	 * @return
	 */
	public String toUpdatePassPage() {
		noteUser= noteUserBo.getOneNoteUserById(noteUser.getUserId());
		return "updatePass";
	}
	
	/**
	 * 提交密码更新
	 * @return
	 */
	public String updatePassSubmit() {
		NoteUser updateNoteUser = noteUserBo.getOneNoteUserById(noteUser.getUserId());
		updateNoteUser.setUserPassword(MD5Utils.md5(noteUser.getUserPassword()));
		updateNoteUser.setState("1");
		updateNoteUser.setToken(UUID.randomUUID().toString());
		noteUserBo.update(updateNoteUser);
		return "indexAction";
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
