package com.duiyi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.ResultCodeData;
import com.duiyi.domain.User;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.UserService;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;
import com.duiyi.utils.MD5Util;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultCodeData result = new ResultCodeData();
		// 检查验证码是否正确
		String validateStr = request.getParameter("validateStr");
		String str2 = request.getSession().getAttribute("validateStr").toString();
		if (validateStr.isEmpty() || str2.isEmpty() || !validateStr.equals(str2)) {
			result.setResult(Constants.FAIL);
			result.setCode(Constants.VALIDATESTR_WRONG);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
			return;
		}
		
		// 准备数据，注册用户
		User user = new User(request.getParameterMap());
		user.setPassword(MD5Util.md5(user.getPassword()));
		UserService service = BasicFactory.getFactory().getService(UserService.class);
		// 注册用户并发送激活邮件
		int code = service.registUser(user);
		if (code != Constants.RESULT_SUCCESS) {
			result.setResult(Constants.SUCCESS);
		} else {
			result.setResult(Constants.FAIL);
		}
		result.setCode(code);
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
