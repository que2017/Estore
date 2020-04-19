package com.duiyi.dao;

import com.duiyi.domain.User;

public interface UserDao extends Dao {
	/**
	 * ͨ���ֶ����ƺ�ֵ��Ѱ���û�
	 *
	 * @param tagName �ֶ�����
	 * @param tagValue �ֶ�ֵ
	 * @return �û�
	 */
	User findUser(String tagName, String tagValue);

	/**
	 * ����û�
	 *
	 * @param user �û�
	 */
	void addUser(User user);

	/**
	 * �����û�
	 *
	 * @param user
	 */
	void updateUser(User user);

}
