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
	 * @return �����룬0���ɹ���202���û�����ע�ᣬ203��������ע�ᣬ204������
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
