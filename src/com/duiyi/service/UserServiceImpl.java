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
		// 检查用户名是否被注册过
		if (dao.findUser("username", user.getUsername()) != null) {
			return Constants.USERNAME_ALREADY_REGISTED;
		}
		// 检查邮箱是否被注册过
		if (dao.findUser("email", user.getEmail()) != null) {
			return Constants.EMAIL_ALREADY_REGISTED;
		}
		// 准备数据
		user.setRole("user");
		user.setState(Constants.USER_UNACTIVED);
		user.setActivecode(UUID.randomUUID().toString());
		// 注册用户并发送激活邮件
		try {
			dao.addUser(user);
			// 发送激活邮件
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
