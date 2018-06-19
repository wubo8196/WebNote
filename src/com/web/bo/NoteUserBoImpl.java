package com.web.bo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.web.dao.NoteUserDao;
import com.web.po.NoteUser;
import com.web.po.Order;
import com.web.utils.MailUtil;
import com.web.utils.ReadModel;
import com.web.utils.VipDateUtil;

@Transactional
public class NoteUserBoImpl implements NoteUserBo {

	private NoteUserDao noteUserDao;
	private OrderBo orderBo;

	@Override
	public NoteUser userRegist(NoteUser noteUser) {
		return noteUserDao.saveOneNoteUser(noteUser);
	}

	@Override
	public NoteUser getOneNoteUserByCriteria(DetachedCriteria detachedCriteria) {
		List<NoteUser> noteUserList = noteUserDao.findNoteUserByCriteria(detachedCriteria);
		return noteUserList == null || !(noteUserList.size() > 0) ? null : noteUserList.get(0);
	}

	@Override
	public void update(NoteUser noteUser) {
		noteUserDao.update(noteUser);
	}

	@Override
	public NoteUser getOneNoteUserById(Long userId) {
		return noteUserDao.getOneNoteUserById(userId);
	}

	@Override
	public boolean addVipDate(NoteUser noteUser, Order order) {
		try {
			Date vipEndDate = noteUser.getVipEndDate();
			int addMonth = (int) (order.getPrice() / 10);
			/**
			 * 会员已到期或首次续费
			 */
			if (vipEndDate.getTime()<=VipDateUtil.getStartDate().getTime()) {
				noteUser.setPrivilege(noteUser.getPrivilege() + 10L);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(VipDateUtil.getStartDate());
				calendar.add(Calendar.MONTH, addMonth);
				noteUser.setVipEndDate(calendar.getTime());
			}
			/**
			 * 会员未到期
			 */
			else if (vipEndDate.getTime()>VipDateUtil.getStartDate().getTime()) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(noteUser.getVipEndDate());
				calendar.add(Calendar.MONTH, addMonth);
				noteUser.setVipEndDate(calendar.getTime());
			}
			String html = ReadModel.readModel("mailModel/paySuccess.html");
			html = html.replace("{userName}", noteUser.getUserName());
			html = html.replace("{vipEndDate}",new SimpleDateFormat("yyyy-MM-dd").format(noteUser.getVipEndDate()));
			MailUtil.sendEmil(noteUser.getUserEmail(), "支付成功", html);
			order.setState(1L);
			update(noteUser);
			orderBo.updateOneOrder(order);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public void setNoteUserDao(NoteUserDao noteUserDao) {
		this.noteUserDao = noteUserDao;
	}

	public void setOrderBo(OrderBo orderBo) {
		this.orderBo = orderBo;
	}

	@Override
	public void toFindPass(NoteUser noteUser) throws IOException {
		NoteUser updateNoteUser = noteUserDao.getOneNoteUserByEmail(noteUser.getUserEmail());
		updateNoteUser.setToken((UUID.randomUUID().toString()));
		updateNoteUser.setState("0");
		String html = ReadModel.readModel("mailModel/findPass.html");
		html = html.replace("{userName}", updateNoteUser.getUserName());
		html = html.replace("{findPassUrl}",
				"http://106.14.124.85" + ":" + ServletActionContext.getRequest().getLocalPort()
						+ ServletActionContext.getRequest().getContextPath() + "/user_toUpdatePassPage.action?userId="
						+ updateNoteUser.getUserId() + "&" + "token=" + updateNoteUser.getToken());
		
		MailUtil.sendEmil( noteUser.getUserEmail(),"密码找回",html );
		noteUserDao.update(updateNoteUser);
		
	}

}
