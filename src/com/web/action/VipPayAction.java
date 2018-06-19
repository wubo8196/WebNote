package com.web.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.bo.NoteUserBo;
import com.web.bo.OrderBo;
import com.web.po.NoteUser;
import com.web.po.Order;
import com.web.po.VipPay;
import com.web.utils.FastJsonUtil;
import com.web.utils.PayUtil;

public class VipPayAction extends ActionSupport {
	private static final long serialVersionUID = 6259788531116632645L;
	private NoteUserBo noteUserBo;
	private OrderBo orderBo;
	private NoteUser noteUser;
	private VipPay vipPay;
	private Order order;
	private String orderid;
	//支付结果 1支付成功
	private Long payResult=0L;
	public String payPre() {
		noteUser = noteUserBo.getOneNoteUserById(this.noteUser.getUserId());
		return "payVip";
	}
	
	/**
	 * 提交支付装载参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String returnPaySubmit() throws UnsupportedEncodingException {
		Order order = orderBo.createOneOrder(this.order);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> remoteMap = new HashMap<String, Object>();
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		remoteMap.put("price",df.format(order.getPrice()));
		remoteMap.put("istype", order.getIstype());
		remoteMap.put("orderid",order.getVipOrderId());
		remoteMap.put("orderuid", order.getUserId());
		remoteMap.put("goodsname", "会员充值");
		resultMap.put("data", PayUtil.payOrder(remoteMap));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String result = FastJsonUtil.toJSONString(resultMap);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			out.write("error");
		}
		return NONE;
	}
	
	public String pay_return () {
		order = orderBo.getOneOrderById(Long.valueOf(orderid));
		if(order.getState().longValue()==1L) {
			payResult=1L;
		}else if(order.getState().longValue()==0L) {
			payResult=2L;
		}
		return "indexAction";
	}
	
	public NoteUser getNoteUser() {
		return noteUser;
	}
	public void setNoteUser(NoteUser noteUser) {
		this.noteUser = noteUser;
	}
	public VipPay getVipPay() {
		return vipPay;
	}
	public void setVipPay(VipPay vipPay) {
		this.vipPay = vipPay;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}
	public void setOrderBo(OrderBo orderBo) {
		this.orderBo = orderBo;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public void setPayResult(Long payResult) {
		this.payResult = payResult;
	}
	public Long getPayResult() {
		return payResult;
	}
}
