package com.web.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.web.po.Order;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	@Override
	public Order saveOneOrder(Order createOrder) {
		this.getHibernateTemplate().save(createOrder);
		return createOrder;
	}

	@Override
	public Order getOneOrderById(Long valueOf) {
		return this.getHibernateTemplate().get(Order.class, valueOf);
	}

	@Override
	public void updateOneOrder(Order order) {
		this.getHibernateTemplate().update(order);
		
	}

}
