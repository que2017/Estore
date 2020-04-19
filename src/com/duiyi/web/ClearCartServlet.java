package com.duiyi.web;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Product;
import com.duiyi.domain.ResultCodeData;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;

public class ClearCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute(Constants.CART_MAP, new LinkedHashMap<Product, Integer>());
		ResultCodeData result = new ResultCodeData(Constants.SUCCESS, Constants.RESULT_SUCCESS);
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
