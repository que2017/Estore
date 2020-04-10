package com.duiyi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.duiyi.domain.ResultCodeData;
import com.duiyi.domain.User;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;

public class CheckSessionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultCodeData result;
		HttpSession session = request.getSession(false);
		if (session == null) {
			result = new ResultCodeData(Constants.FAIL, Constants.SESSION_INVALID);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			result = new ResultCodeData(Constants.FAIL, Constants.SESSION_NO_USER);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
		} else {
			result = new ResultCodeData(Constants.SUCCESS, Constants.RESULT_SUCCESS);
			String ret = result.toString() + ",\'user\':" + JSONUtil.buildJsonString(user.toString());
			response.getWriter().write(JSONUtil.buildJsonString(ret));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
