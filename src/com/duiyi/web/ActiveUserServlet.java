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

public class ActiveUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultCodeData result = new ResultCodeData();
		String activeCode = request.getParameter("activecode");
		
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		User user = service.findUser("activecode", activeCode);
		if (user != null) {
			if (user.getState() == Constants.USER_ACTIVED) {
				// �û��Ѽ���
				result.setResult(Constants.FAIL);
				result.setCode(Constants.ACTIVE_CODE_ACTIVED);
			} else if (System.currentTimeMillis() - user.getUpdatetime().getTime() > 2 * 3600 * 1000) {
				// ������Сʱ����������ʧЧ
				result.setResult(Constants.FAIL);
				result.setCode(Constants.ACTIVE_CODE_EXPIRED);
			} else {
				result.setResult(Constants.SUCCESS);
				result.setCode(Constants.RESULT_SUCCESS);
				// �����û�Ϊ����״̬
				user.setState(Constants.USER_ACTIVED);
				service.updateUser(user);
			}
		} else {
			// δ�ҵ��û�
			result.setResult(Constants.FAIL);
			result.setCode(Constants.ACTIVE_CODE_WRONG);
		}
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
