package com.duiyi.service;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.domain.User;

public interface UserService extends Service {
	/**
	 * 通过字段名称和值，寻找用户
	 *
	 * @param tagName 字段名称
	 * @param tagValue 字段值
	 * @return 用户
	 */
	User findUser(String tagName, String tagValue);

	/**
	 * 注册用户
	 *
	 * @param user 用户
	 * @return 返回码，0：成功，202：用户名已注册，203：邮箱已注册，204：其他
	 */
	@NeedTrans
	int registUser(User user);

	/**
	 * 更新用户
	 *
	 * @param user
	 */
	void updateUser(User user);
}
