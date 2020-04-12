package com.duiyi.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Product;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.ProductService;
import com.duiyi.utils.JSONUtil;

public class ListAllProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		// 查询所有商品
		List<Product> list = service.findAllProduct();
		response.getWriter().write(JSONUtil.buildJsonString("\'productlist\':" + JSONUtil.buildJsonArrayString(list)));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
