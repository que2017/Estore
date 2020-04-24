package com.duiyi.service;

import java.util.List;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.domain.Order;

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

}
