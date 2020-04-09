package com.duiyi.service;

import com.duiyi.domain.User;

public interface UserService {
	/**
	 * ͨ���ֶ����ƺ�ֵ��Ѱ���û�
	 *
	 * @param tagName �ֶ�����
	 * @param tagValue �ֶ�ֵ
	 * @return �û�
	 */
	User findUser(String tagName, String tagValue);

	/**
	 * ע���û�
	 *
	 * @param user �û�
	 * @return �����룬0���ɹ���3���û�����ע�ᣬ4��������ע�ᣬ5������
	 */
	int registUser(User user);

	/**
	 * �����û�
	 *
	 * @param user
	 */
	void updateUser(User user);
}
