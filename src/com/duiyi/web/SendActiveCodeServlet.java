package com.duiyi.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.ResultCodeData;
import com.duiyi.domain.User;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.UserService;
import com.duiyi.utils.Constants;
import com.duiyi.utils.EmailUtil;
import com.duiyi.utils.JSONUtil;

public class SendActiveCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultCodeData result = new ResultCodeData();
		String email = request.getParameter("email");
		UserService service = BasicFactory.getFactory().getService(UserService.class);
		User user = service.findUser("email", email);
		if (user != null) {
			// ���ҵ�����
			if (user.getState() == Constants.USER_UNACTIVED) {
				// �û�δ������ͼ����ʼ�
				user.setActivecode(UUID.randomUUID().toString());
				user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
				try {
					EmailUtil.sendActiveEmail(user.getUsername(), user.getEmail(), user.getActivecode());
				} catch (MessagingException e) {
					e.printStackTrace();
					// �ʼ�����ʧ��
					result.setResult(Constants.FAIL);
					result.setCode(Constants.EMAIL_SEND_FAIL);
					response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
					return;
				}
				service.updateUser(user);
				result.setResult(Constants.SUCCESS);
				result.setCode(Constants.RESULT_SUCCESS);
			} else {
				// �û��Ѽ���
				result.setResult(Constants.FAIL);
				result.setCode(Constants.EMAIL_ACTIVED);
			}
		} else {
			// δ���ҵ�����
			result.setResult(Constants.FAIL);
			result.setCode(Constants.EMAIL_UNREGIST);
		}
		
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
