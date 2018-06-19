package com.web.po;

/**
 * 支付接口请求参数
 * @author 吴波
 *
 */
public class VipPay {
	/**
	 * 商户uid 
	 */
	private String uid;
	/**
	 * 价格
	 */
	private Float price;
	
	/**
	 * 支付渠道
	 */
	private Integer istype;
	
	/**
	 * 通知回掉网址
	 */
	private String notify_url;
	
	/**
	 * 跳转网址
	 */
	private String return_url;
	
	/**
	 * 商户自定义订单号
	 */
	private String orderid;
	
	/**
	 * 商户自定义客户号
	 */
	private String orderuid;
	
	/**
	 * 商品名称
	 */
	private String goodsname;
	
	/**
	 * 秘钥
	 */
	private String key;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getIstype() {
		return istype;
	}

	public void setIstype(Integer istype) {
		this.istype = istype;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderuid() {
		return orderuid;
	}

	public void setOrderuid(String orderuid) {
		this.orderuid = orderuid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
