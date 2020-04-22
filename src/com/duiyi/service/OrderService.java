package com.duiyi.service;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.domain.Order;

public interface OrderService extends Service {
	/**
	 * Ìí¼Ó¶©µ¥
	 *
	 * @param order
	 * @return
	 */
	@NeedTrans
	Integer addOrder(Order order);

}
