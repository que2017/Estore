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

	public Map<Product, Integer> findProductsByOrderId(String orderId) {
		Map<Product, Integer> map = new HashMap<Product, Integer>();
		// 1.根据orderId，从orderitem表中查出所有的商品product_id和购买数量buynum
		List<OrderItem> orderItemList = orderItemDao.findProductsByOrderId(orderId);
		// 2.根据查到的product_id，从product表中查找商品信息Product
		for (OrderItem item : orderItemList) {
			Product prod = productDao.findProductById(item.getProduct_id());
			map.put(prod, item.getBuynum());
		}
		// 3.将Product和buynum存入map中
		return map;
	}

	public Integer deleteOrderById(String orderId) {
		// 1.查找出所有List<OrderItem>，将对应的product的pnum添加回去
		List<OrderItem> orderItemList = orderItemDao.findProductsByOrderId(orderId);
		for (OrderItem item : orderItemList) {
			productDao.increasePnum(item.getProduct_id(), item.getBuynum());
		}
		// 2.根据orderId，从orderitem表删除对应订单商品项
		orderItemDao.deleteOrderItem("order_id", orderId);
		// 3.根据orderId，从orders表中删除订单，这一步必须在第2步后面，因为order_id是orderitem表的外键
		// 以上3步要在同一个事物中
		orderDao.deleteOrderById(orderId);
		return Constants.RESULT_SUCCESS;
	}

	public Order findOrderById(String orderId) {
		return orderDao.findOrderById(orderId);
	}

}
