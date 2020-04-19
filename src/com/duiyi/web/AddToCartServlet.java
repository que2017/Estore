package com.duiyi.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Product;
import com.duiyi.domain.ResultCodeData;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.ProductService;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;

public class AddToCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductService service = BasicFactory.getFactory().getService(ProductService.class);
		Product prod = service.findProductById(id);
		ResultCodeData result;
		if (prod == null) {
			result = new ResultCodeData(Constants.FAIL, Constants.NOT_FIND_PRODUCT);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
			return;
		}
		Map<Product, Integer> cartMap = (Map<Product, Integer>) request.getSession().getAttribute(Constants.CART_MAP);
		cartMap.put(prod, cartMap.containsKey(prod) ? cartMap.get(prod) + 1 : 1);
		result = new ResultCodeData(Constants.SUCCESS, Constants.RESULT_SUCCESS);
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
