package com.duiyi.service;

import java.util.UUID;

import com.duiyi.dao.UserDao;
import com.duiyi.domain.User;
import com.duiyi.factory.BasicFactory;
import com.duiyi.utils.Constants;
import com.duiyi.utils.EmailUtil;

public class UserServiceImpl implements UserService {
	private UserDao dao = BasicFactory.getFactory().getDao(UserDao.class);

	public User findUser(String tagName, String tagValue) {
		return dao.findUser(tagName, tagValue);
	}

	public Integer registUser(User user) {
		// ����û����Ƿ�ע���
		if (dao.findUser("username", user.getUsername()) != null) {
			return Constants.USERNAME_ALREADY_REGISTED;
		}
		// ��������Ƿ�ע���
		if (dao.findUser("email", user.getEmail()) != null) {
			return Constants.EMAIL_ALREADY_REGISTED;
		}
		// ׼������
		user.setRole("user");
		user.setState(Constants.USER_UNACTIVED);
		user.setActivecode(UUID.randomUUID().toString());
		// ע���û������ͼ����ʼ�
		try {
			dao.addUser(user);
			// ���ͼ����ʼ�
			EmailUtil.sendActiveEmail(user.getUsername(), user.getEmail(), user.getActivecode());
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException(e);
		}
		
		return Constants.RESULT_SUCCESS;
	}

	public void updateUser(User user) {
		dao.updateUser(user);
	}

}
