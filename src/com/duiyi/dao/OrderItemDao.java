package com.duiyi.dao;

import java.sql.SQLException;

import com.duiyi.domain.OrderItem;

public interface OrderItemDao extends Dao {
	/**
	 * ��Ӷ�����
	 *
	 * @param item
	 * @throws SQLException 
	 */
	void addOrderItem(OrderItem item) throws SQLException;

}
