package com.duiyi.service;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.domain.User;

public interface UserService extends Service {
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
	@NeedTrans
	int registUser(User user);

	/**
	 * �����û�
	 *
	 * @param user
	 */
	void updateUser(User user);
}
