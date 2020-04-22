package com.duiyi.dao;

import java.sql.SQLException;

import com.duiyi.domain.Order;

public interface OrderDao extends Dao {
	/**
	 * Ìí¼Ó¶©µ¥
	 *
	 * @param order
	 * @throws SQLException 
	 */
	void addOrder(Order order) throws SQLException;

}
