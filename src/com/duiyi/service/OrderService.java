package com.duiyi.service;

import com.duiyi.domain.Order;

public interface OrderService extends Service {
	/**
	 * ��Ӷ���
	 *
	 * @param order
	 * @return
	 */
	int addOrder(Order order);

}
