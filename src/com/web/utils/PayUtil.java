package com.web.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.po.PaySaPi;

public class PayUtil {
	public static String UID = "a2aedec77ec45dbcabf6c23b";

	public static String NOTIFY_URL = "http://106.14.124.85:8080/WebNote/payNotifyAction_notify_url.action";

	public static String RETURN_URL = "http://106.14.124.85:8080/WebNote/vipPayAction_pay_return.action";

	public static String BASE_URL = "https://pay.paysapi.com";

	public static String TOKEN = "4f1df097148f5aaaff7bf4776c44c83e";
	final static Logger logger = Logger.getLogger(PayUtil.class);
	
	/**
	 * 装载支付接口参数
	 * @param remoteMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, Object> payOrder(Map<String, Object> remoteMap) throws UnsupportedEncodingException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", UID);
		paramMap.put("notify_url", NOTIFY_URL);
		paramMap.put("return_url", RETURN_URL);
		paramMap.putAll(remoteMap);
		paramMap.put("key", getKey(paramMap));
		return paramMap;
	}
	
	/**
	 * 加密成密文
	 * @param remoteMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getKey(Map<String, Object> remoteMap) throws UnsupportedEncodingException {
		String key = "";
		if (null != remoteMap.get("goodsname")) {
			key += remoteMap.get("goodsname");
		}
		if (null != remoteMap.get("istype")) {
			key += remoteMap.get("istype");
		}
		if (null != remoteMap.get("notify_url")) {
			key += remoteMap.get("notify_url");
		}
		if (null != remoteMap.get("orderid")) {
			key += remoteMap.get("orderid");
		}
		if (null != remoteMap.get("orderuid")) {
			key += remoteMap.get("orderuid");
		}
		if (null != remoteMap.get("price")) {
			key += remoteMap.get("price");
		}
		if (null != remoteMap.get("return_url")) {
			key += remoteMap.get("return_url");
		}
		key += TOKEN;
		if (null != remoteMap.get("uid")) {
			key += remoteMap.get("uid");
		}
		return MD5Utils.encryption(key);
	}
	
	public static boolean checkPayKey(PaySaPi paySaPi) throws UnsupportedEncodingException {
		String key = "";
		if (!StringUtils.isBlank(paySaPi.getOrderid())) {
			logger.info("支付回来的订单号：" + paySaPi.getOrderid());
			key += paySaPi.getOrderid();
		}
		if (!StringUtils.isBlank(paySaPi.getOrderuid())) {
			logger.info("支付回来的支付记录的ID：" + paySaPi.getOrderuid());
			key += paySaPi.getOrderuid();
		}
		if (!StringUtils.isBlank(paySaPi.getPaysapi_id())) {
			logger.info("支付回来的平台订单号：" + paySaPi.getPaysapi_id());
			key += paySaPi.getPaysapi_id();
		}
		if (!StringUtils.isBlank(paySaPi.getPrice())) {
			logger.info("支付回来的价格：" + paySaPi.getPrice());
			key += paySaPi.getPrice();
		}
		if (!StringUtils.isBlank(paySaPi.getRealprice())) {
			logger.info("支付回来的真实价格：" + paySaPi.getRealprice());
			key += paySaPi.getRealprice();
		}
		logger.info("支付回来的Key：" + paySaPi.getKey());
		key += TOKEN;
		logger.info("我们自己拼接的Key：" + MD5Utils.encryption(key));
		return paySaPi.getKey().equals(MD5Utils.encryption(key));
	}
	
	
}
