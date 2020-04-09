package com.duiyi.dao;

import java.sql.Connection;

import com.duiyi.domain.User;

public interface UserDao {
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
	 * @param conn ���ݿ�����
	 */
	void addUser(User user, Connection conn);

	/**
	 * �����û�
	 *
	 * @param user
	 */
	void updateUser(User user);

}
