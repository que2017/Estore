package com.duiyi.service;

import java.util.List;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.domain.Order;

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

}
