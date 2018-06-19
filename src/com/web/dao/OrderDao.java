package com.web.dao;

import com.web.po.Order;

public interface OrderDao {

	/**
	 * 保存一个订单
	 * @param createOrder
	 * @return 
	 */
	Order saveOneOrder(Order createOrder);

	/**
	 * 获取一个订单
	 * @param valueOf
	 * @return
	 */
	Order getOneOrderById(Long valueOf);

	/**
	 * 更新一个订单
	 * @param order
	 */
	void updateOneOrder(Order order);

}
