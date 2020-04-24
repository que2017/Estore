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
			// 1.在订单表orders中插入订单项
			orderDao.addOrder(order);
			for (OrderItem item : order.getList()) {
				// 2.在订单项表orderitem中更新订单中的商品数量
				orderItemDao.addOrderItem(item);
				// 3.在商品表products中扣除商品数量，并且以上3步要在同一个事物中处理
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
