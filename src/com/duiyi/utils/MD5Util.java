package com.duiyi.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * ʹ��MD5�㷨���м���
	 *
	 * @param text �����ܵ��ַ���
	 * @return ���ܺ���ַ���
	 */
	public static String md5(String text) {
		byte[] secret = null;
		try {
			secret= MessageDigest.getInstance("md5").digest(text.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		String result= new BigInteger(1, secret).toString(16);
		for (int i = 0; i < 32 - result.length(); i++) {
			result = "0" + result;
		}
		return result;
	}
	
	private MD5Util() {
	}
}
