package com.duiyi.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Product;
import com.duiyi.domain.ResultCodeData;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;

public class DeleteCartProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ResultCodeData result;
		Map<Product, Integer> cartMap = (Map<Product, Integer>) request.getSession().getAttribute(Constants.CART_MAP);
		for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
			if (entry.getKey().getId().equals(id)) {
				cartMap.remove(entry.getKey());
				result = new ResultCodeData(Constants.SUCCESS, Constants.RESULT_SUCCESS);
				response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
				return;
			}
		}
		result = new ResultCodeData(Constants.FAIL, Constants.NOT_FIND_PRODUCT);
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
