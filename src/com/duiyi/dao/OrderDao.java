package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.domain.Order;

public interface OrderDao extends Dao {
	/**
	 * 添加订单
	 *
	 * @param order
	 * @throws SQLException 
	 */
	void addOrder(Order order) throws SQLException;

	/**
	 * 根据用户id查找订单
	 *
	 * @param id
	 * @return
	 */
	List<Order> findOrdersByUserId(int id);

}
