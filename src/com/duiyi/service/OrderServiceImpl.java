package com.duiyi.service;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.dao.OrderDao;
import com.duiyi.dao.OrderItemDao;
import com.duiyi.dao.ProductDao;
import com.duiyi.domain.Order;
import com.duiyi.domain.OrderItem;
import com.duiyi.factory.BasicFactory;
import com.duiyi.utils.Constants;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
	
	private OrderItemDao orderItemDao = BasicFactory.getFactory().getDao(OrderItemDao.class);
	
	private ProductDao productDao = BasicFactory.getFactory().getDao(ProductDao.class);
	
	public Integer addOrder(Order order) {
		try {
			// 1.�ڶ�����orders�в��붩����
			orderDao.addOrder(order);
			for (OrderItem item : order.getList()) {
				// 2.�ڶ������orderitem�и��¶����е���Ʒ����
				orderItemDao.addOrderItem(item);
				// 3.����Ʒ��products�п۳���Ʒ��������������3��Ҫ��ͬһ�������д���
				productDao.reducePnum(item.getProduct_id(), item.getBuynum());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return Constants.RESULT_SUCCESS;
	}

	public List<Order> findOrdersByUserId(int id) {
		return orderDao.findOrdersByUserId(id);
	}

}
