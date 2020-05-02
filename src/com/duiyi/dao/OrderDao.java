package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.domain.Order;
import com.duiyi.domain.SaleInfo;

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

	/**
	 * 根据orderId删除订单
	 *
	 * @param orderId
	 * @throws SQLException 
	 */
	void deleteOrderById(String orderId);

	/**
	 * 根据订单号查询订单
	 *
	 * @param orderId
	 * @return
	 */
	Order findOrderById(String orderId);

	/**
	 * 修改订单支付状态
	 *
	 * @param orderId
	 * @param state
	 */
	void changeOrderState(String orderId, int state);

	/**
	 * 获取销售榜单
	 *
	 * @return
	 */
	List<SaleInfo> getSaleList();

}
