package com.duiyi.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * 使用MD5算法进行加密
	 *
	 * @param text 待加密的字符串
	 * @return 加密后的字符串
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
