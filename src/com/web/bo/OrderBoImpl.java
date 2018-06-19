package com.web.bo;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.web.dao.OrderDao;
import com.web.po.NoteUser;
import com.web.po.Order;

@Transactional
public class OrderBoImpl implements OrderBo {
	private NoteUserBo noteUserBo;
	private OrderDao orderDao;

	public void setNoteUserBo(NoteUserBo noteUserBo) {
		this.noteUserBo = noteUserBo;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public Order createOneOrder(Order order) {
		NoteUser noteUser = null;
		if (order.getUserId() != null) {
			noteUser = noteUserBo.getOneNoteUserById(order.getUserId());
		} else {
			return null;
		}
		if (noteUser != null && order.getMonth() != null && order.getIstype() != null) {
			Order createOrder = new Order();
			createOrder.setCreateDate(new Date());
			createOrder.setIstype(order.getIstype());
			createOrder.setMonth(order.getMonth());
			createOrder.setPrice(order.getMonth().longValue() * 10.0);
			createOrder.setState(0L);
			createOrder.setUserId(noteUser.getUserId());
			createOrder = orderDao.saveOneOrder(createOrder);
			return createOrder;
		} else {
			return null;

		}
	}

	@Override
	public Order getOneOrderById(Long valueOf) {
		return orderDao.getOneOrderById(valueOf);
	}

	@Override
	public void updateOneOrder(Order order) {
		orderDao.updateOneOrder(order);
		
	}

}
