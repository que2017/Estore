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

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultCodeData result = new ResultCodeData();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		User user = service.findUser("username", username);
		if (user != null) {
			// ���ҵ��û�
			if (user.getState() == Constants.USER_ACTIVED) {
				// �û�����
				if (user.getPassword().equals(password)) {
					// ������ȷ
					result.setResult(Constants.SUCCESS);
					result.setCode(Constants.RESULT_SUCCESS);
				} else {
					// ���벻��ȷ
					result.setResult(Constants.FAIL);
					result.setCode(Constants.USERNAME_PASSWORD_WRONG);
				}
			} else {
				// �û�δ����
				result.setResult(Constants.FAIL);
				result.setCode(Constants.USER_UNACTIVED);
			}
		} else {
			// δ���ҵ��û�
			result.setResult(Constants.FAIL);
			result.setCode(Constants.USER_NOT_EXIST);
		}
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
