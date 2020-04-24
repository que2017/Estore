package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.domain.OrderItem;

public interface OrderItemDao extends Dao {
	/**
	 * ��Ӷ�����
	 *
	 * @param item
	 * @throws SQLException 
	 */
	void addOrderItem(OrderItem item) throws SQLException;

	/**
	 * ���ݶ����Ų�ѯ��Ʒ
	 *
	 * @param id
	 * @return
	 */
	List<OrderItem> findProductsByOrderId(String id);

}
