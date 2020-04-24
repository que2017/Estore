package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.domain.OrderItem;

public interface OrderItemDao extends Dao {
	/**
	 * 添加订单项
	 *
	 * @param item
	 * @throws SQLException 
	 */
	void addOrderItem(OrderItem item) throws SQLException;

	/**
	 * 根据订单号查询商品
	 *
	 * @param id
	 * @return
	 */
	List<OrderItem> findProductsByOrderId(String id);

}
