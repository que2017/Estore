package com.duiyi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Order;
import com.duiyi.domain.OrderItem;
import com.duiyi.domain.Product;
import com.duiyi.domain.User;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.OrderService;
import com.duiyi.utils.Constants;

public class AddOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setPaystate(Constants.ORDER_NOT_PAYED);
		// ���ö�����ַ
		order.setReceiverinfo(request.getParameter("address"));
		
		// ���㶩�������ɶ�����Ʒ�б�
		double totalPrice = 0.0;
		List<OrderItem> list = new ArrayList<OrderItem>(); 
		Map<Product, Integer> cartMap = (Map<Product, Integer>) request.getSession().getAttribute(Constants.CART_MAP);
		for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
			totalPrice += entry.getKey().getPrice() * entry.getValue();
			OrderItem orderItem = new OrderItem(order.getId(), entry.getKey().getId(), entry.getValue());
			list.add(orderItem);
		}
		order.setMoney(totalPrice);
		order.setList(list);
		// ���ÿͻ����
		order.setUser_id(((User) request.getSession().getAttribute("user")).getId());
		
		// ��Ӷ�����order��������orderitem����ҲҪͬ�����
		OrderService service = BasicFactory.getFactory().getService(OrderService.class);
		int code = service.addOrder(order);
		// ��չ��ﳵ
		cartMap.clear();
		
		response.getWriter().write("aaa");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
