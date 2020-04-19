package com.duiyi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Product;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.ProductService;
import com.duiyi.utils.JSONUtil;

public class ProductDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductService service = BasicFactory.getFactory().getService(ProductService.class);
		Product prod = service.findProductById(id);
		response.getWriter().write(JSONUtil.buildJsonString(prod.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
