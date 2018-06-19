package com.web.bo;

import com.web.po.Order;

public interface OrderBo {

	/**
	 * 创建一个订单
	 * @param order
	 * @return
	 */
	Order createOneOrder(Order order);

	/**
	 * 通过id获取订单
	 * @param valueOf
	 * @return
	 */
	Order getOneOrderById(Long valueOf);

	/**
	 * 更新订单
	 * @param order
	 */
	void updateOneOrder(Order order);

}
