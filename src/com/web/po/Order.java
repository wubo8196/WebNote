package com.web.po;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	private static final long serialVersionUID = -684911375080221120L;
	/**
	 * 订单id
	 */
	private Long vipOrderId;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 状态 0未支付 1已支付
	 */
	private Long state;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 开通会员月数
	 */
	private Long month;
	
	/**
	 * 支付渠道
	 */
	private Integer istype;
	
	/**
	 * 支付金额
	 */
	private Double price;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Integer getIstype() {
		return istype;
	}

	public void setIstype(Integer istype) {
		this.istype = istype;
	}

	public Long getVipOrderId() {
		return vipOrderId;
	}

	public void setVipOrderId(Long vipOrderId) {
		this.vipOrderId = vipOrderId;
	}
	
	
	

}
