package com.duiyi.service;

import java.util.List;
import java.util.Map;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.domain.Order;
import com.duiyi.domain.Product;

public interface OrderService extends Service {
	/**
	 * 添加订单
	 *
	 * @param order
	 * @return
	 */
	@NeedTrans
	Integer addOrder(Order order);

	/**
	 * 根据用户id查找订单
	 *
	 * @param id
	 * @return
	 */
	List<Order> findOrdersByUserId(int id);

	/**
	 * 根据订单号查询商品
	 *
	 * @param id
	 * @return
	 */
	Map<Product, Integer> findProductsByOrderId(String id);

	/**
	 * 根据订单号删除订单
	 *
	 * @param orderId
	 * @return
	 */
	@NeedTrans
	Integer deleteOrderById(String orderId);

}
