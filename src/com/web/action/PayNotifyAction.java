package com.web.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.bo.NoteUserBo;
import com.web.bo.OrderBo;
import com.web.po.NoteUser;
import com.web.po.Order;
import com.web.po.PaySaPi;
import com.web.utils.PayUtil;

/**
 * 支付成功通知
 * @author 吴波
 *
 */
public class PayNotifyAction extends ActionSupport implements ModelDriven<PaySaPi> {
	private static final long serialVersionUID = -5141623813140665666L;
	private NoteUserBo noteUserBo;
	private OrderBo orderBo;
	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}
	public void setOrderBo(OrderBo orderBo) {
		this.orderBo = orderBo;
	}
	private PaySaPi paySapi = new PaySaPi();
	@Override
	public PaySaPi getModel() {
		// TODO Auto-generated method stub
		return paySapi;
	}
	public String notify_url() throws UnsupportedEncodingException {
		if(PayUtil.checkPayKey(paySapi)) {
			NoteUser noteUser = noteUserBo.getOneNoteUserById(Long.valueOf(paySapi.getOrderuid()));
			Order order = orderBo.getOneOrderById(Long.valueOf(paySapi.getOrderid()));
			noteUserBo.addVipDate(noteUser,order);
		}
		return NONE;
	}

	
}
