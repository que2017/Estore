package com.duiyi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.ResultCodeData;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.OrderService;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;

public class DeleteOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService service = BasicFactory.getFactory().getService(OrderService.class);
		String orderId = request.getParameter("orderId");
		Integer code = service.deleteOrderById(orderId);
		ResultCodeData result;
		if (code != null && code.equals(Constants.RESULT_SUCCESS)) {
			result = new ResultCodeData(Constants.SUCCESS, Constants.RESULT_SUCCESS);
		} else {
			result = new ResultCodeData(Constants.FAIL, Constants.RESULT_FAIL);
		}
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
