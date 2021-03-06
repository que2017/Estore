package com.duiyi.dao;

import com.duiyi.domain.User;

public interface UserDao extends Dao {
	/**
	 * 通过字段名称和值，寻找用户
	 *
	 * @param tagName 字段名称
	 * @param tagValue 字段值
	 * @return 用户
	 */
	User findUser(String tagName, String tagValue);

	/**
	 * 添加用户
	 *
	 * @param user 用户
	 */
	void addUser(User user);

	/**
	 * 更新用户
	 *
	 * @param user
	 */
	void updateUser(User user);

}
