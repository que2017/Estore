package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.domain.Order;
import com.duiyi.domain.SaleInfo;

public interface OrderDao extends Dao {
	/**
	 * ��Ӷ���
	 *
	 * @param order
	 * @throws SQLException 
	 */
	void addOrder(Order order) throws SQLException;

	/**
	 * �����û�id���Ҷ���
	 *
	 * @param id
	 * @return
	 */
	List<Order> findOrdersByUserId(int id);

	/**
	 * ����orderIdɾ������
	 *
	 * @param orderId
	 * @throws SQLException 
	 */
	void deleteOrderById(String orderId);

	/**
	 * ���ݶ����Ų�ѯ����
	 *
	 * @param orderId
	 * @return
	 */
	Order findOrderById(String orderId);

	/**
	 * �޸Ķ���֧��״̬
	 *
	 * @param orderId
	 * @param state
	 */
	void changeOrderState(String orderId, int state);

	/**
	 * ��ȡ���۰�
	 *
	 * @return
	 */
	List<SaleInfo> getSaleList();

}
