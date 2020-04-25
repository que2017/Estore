package com.duiyi.service;

import java.util.List;
import java.util.Map;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.domain.Order;
import com.duiyi.domain.Product;

public interface OrderService extends Service {
	/**
	 * ��Ӷ���
	 *
	 * @param order
	 * @return
	 */
	@NeedTrans
	Integer addOrder(Order order);

	/**
	 * �����û�id���Ҷ���
	 *
	 * @param id
	 * @return
	 */
	List<Order> findOrdersByUserId(int id);

	/**
	 * ���ݶ����Ų�ѯ��Ʒ
	 *
	 * @param id
	 * @return
	 */
	Map<Product, Integer> findProductsByOrderId(String id);

	/**
	 * ���ݶ�����ɾ������
	 *
	 * @param orderId
	 * @return
	 */
	@NeedTrans
	Integer deleteOrderById(String orderId);

}
