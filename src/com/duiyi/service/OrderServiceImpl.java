package com.duiyi.service;

import com.duiyi.dao.OrderDao;
import com.duiyi.domain.Order;
import com.duiyi.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	private OrderDao dao = BasicFactory.getFactory().getDao(OrderDao.class);
	
	public int addOrder(Order order) {
		return 0;
	}

}
