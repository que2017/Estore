package com.duiyi.utils;

public class Constants {
	public static final String CART_MAP = "cartMap";
	
	public static final String SUCCESS = "succ";
	
	public static final String FAIL = "fail";
	
	public static int RESULT_SUCCESS = 0;
	
	// �û���¼���״̬��
	public static int USERNAME_PASSWORD_WRONG = 101;
	public static int USER_NOT_EXIST = 102;
	public static final int USER_UNACTIVED = 103;
	public static final int USER_ACTIVED = 104;

	// �û�ע�����״̬��
	public static int VALIDATESTR_WRONG = 201;
	public static int USERNAME_ALREADY_REGISTED = 202;
	public static int EMAIL_ALREADY_REGISTED = 203;
	public static int OTHER_RESON = 204;

	// �û��������״̬��
	public static int ACTIVE_CODE_WRONG = 301;
	public static int ACTIVE_CODE_EXPIRED = 302;
	public static int ACTIVE_CODE_ACTIVED = 303;

	// ���·��ͼ����ʼ����״̬��
	public static int EMAIL_UNREGIST = 401;
	public static int EMAIL_ACTIVED = 402;
	public static int EMAIL_SEND_FAIL = 403;
	
	// ���session�Ƿ���Ч
	public static int SESSION_INVALID = 501;
	public static int SESSION_NO_USER = 502;
	
	// �����Ʒ
	public static int FROM_FORMAT_ERROR = 601;
	public static int PARAM_FORMAT_ERROR = 602;
	
	// �����Ʒ�����ﳵ
	public static int NOT_FIND_PRODUCT = 701;
	
	private Constants() {
	}
}
