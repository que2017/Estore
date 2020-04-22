package com.duiyi.dao;

import java.sql.SQLException;

import com.duiyi.domain.OrderItem;

public interface OrderItemDao extends Dao {
	/**
	 * Ìí¼Ó¶©µ¥Ïî
	 *
	 * @param item
	 * @throws SQLException 
	 */
	void addOrderItem(OrderItem item) throws SQLException;

}
