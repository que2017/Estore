package com.duiyi.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.duiyi.dao.OrderDao;
import com.duiyi.dao.OrderItemDao;
import com.duiyi.dao.ProductDao;
import com.duiyi.domain.Order;
import com.duiyi.domain.OrderItem;
import com.duiyi.domain.Product;
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

	public Map<Product, Integer> findProductsByOrderId(String orderId) {
		Map<Product, Integer> map = new HashMap<Product, Integer>();
		// 1.����orderId����orderitem���в�����е���Ʒproduct_id�͹�������buynum
		List<OrderItem> orderItemList = orderItemDao.findProductsByOrderId(orderId);
		// 2.���ݲ鵽��product_id����product���в�����Ʒ��ϢProduct
		for (OrderItem item : orderItemList) {
			Product prod = productDao.findProductById(item.getProduct_id());
			map.put(prod, item.getBuynum());
		}
		// 3.��Product��buynum����map��
		return map;
	}

	public Integer deleteOrderById(String orderId) {
		// 1.���ҳ�����List<OrderItem>������Ӧ��product��pnum��ӻ�ȥ
		List<OrderItem> orderItemList = orderItemDao.findProductsByOrderId(orderId);
		for (OrderItem item : orderItemList) {
			productDao.increasePnum(item.getProduct_id(), item.getBuynum());
		}
		// 2.����orderId����orderitem��ɾ����Ӧ������Ʒ��
		orderItemDao.deleteOrderItem("order_id", orderId);
		// 3.����orderId����orders����ɾ����������һ�������ڵ�2�����棬��Ϊorder_id��orderitem������
		// ����3��Ҫ��ͬһ��������
		orderDao.deleteOrderById(orderId);
		return Constants.RESULT_SUCCESS;
	}

	public Order findOrderById(String orderId) {
		return orderDao.findOrderById(orderId);
	}

}
