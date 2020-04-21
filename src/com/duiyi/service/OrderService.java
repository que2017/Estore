package com.duiyi.service;

import com.duiyi.domain.Order;

public interface OrderService extends Service {
	/**
	 * Ìí¼Ó¶©µ¥
	 *
	 * @param order
	 * @return
	 */
	int addOrder(Order order);

}
