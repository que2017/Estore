package com.duiyi.service;

import java.sql.Connection;
import java.util.UUID;

import org.apache.commons.dbutils.DbUtils;

import com.duiyi.dao.UserDao;
import com.duiyi.domain.User;
import com.duiyi.factory.BasicFactory;
import com.duiyi.utils.Constants;
import com.duiyi.utils.DaoUtil;
import com.duiyi.utils.EmailUtil;

public class UserServiceImpl implements UserService {
	private UserDao dao = BasicFactory.getFactory().getDao(UserDao.class);

	public User findUser(String tagName, String tagValue) {
		return dao.findUser(tagName, tagValue);
	}

	public int registUser(User user) {
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
		Connection conn = null;
		try {
			conn = DaoUtil.getConnection();
			conn.setAutoCommit(false);
			dao.addUser(user, conn);
			
			// ���ͼ����ʼ�
			EmailUtil.sendActiveEmail(user.getUsername(), user.getEmail(), user.getActivecode());
			
			DbUtils.commitAndCloseQuietly(conn);
		} catch (Exception e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			return Constants.OTHER_RESON;
		}
		
		return Constants.RESULT_SUCCESS;
	}

	public void updateUser(User user) {
		dao.updateUser(user);
	}

}
