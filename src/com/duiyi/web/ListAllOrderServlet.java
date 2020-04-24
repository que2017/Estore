package com.duiyi.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.duiyi.utils.JSONUtil;

public class ListAllOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int id = user.getId();
		OrderService orderService = BasicFactory.getFactory().getService(OrderService.class);
		List<Order> list = orderService.findOrdersByUserId(id);
		for (Order order : list) {
			order.setMap(orderService.findProductsByOrderId(order.getId()));
		}
		response.getWriter().write(JSONUtil.buildJsonArrayString(list));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
