package com.duiyi.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Product;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;

public class ListCartProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Product, Integer> cartMap = (Map<Product, Integer>) request.getSession().getAttribute(Constants.CART_MAP);
		response.getWriter().write(getCartMapJsonString(cartMap));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String getCartMapJsonString(Map<Product, Integer> cartMap) {
		if (cartMap == null) {
			return "{}";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
			builder.append(JSONUtil.buildJsonString(entry.getKey().toString() + ",\'num\':\'" + entry.getValue() + "\'"));
			builder.append(",");
		}
		builder.append("]");
		return JSONUtil.buildJsonString("\'cartproductlist\':" + builder.toString());
	}
}
