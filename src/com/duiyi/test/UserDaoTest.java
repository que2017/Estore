package com.duiyi.test;

import org.junit.Test;

import com.duiyi.dao.UserDao;
import com.duiyi.domain.User;
import com.duiyi.factory.BasicFactory;

public class UserDaoTest {
	@Test
	public void findUserTest() {
		UserDao dao = BasicFactory.getFactory().getDao(UserDao.class);
		User user = dao.findUser("username", "zhang");
		System.out.println(user.getPassword());
	}
}
