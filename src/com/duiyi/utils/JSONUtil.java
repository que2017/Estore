package com.duiyi.utils;

import java.util.List;
import java.util.Map;

import com.duiyi.domain.Product;

public class JSONUtil {
	public static String buildJsonString(String string) {
		return "{" + string + "}";
	}
	
	public static <T> String buildJsonArrayString(List<T> list) {
		if (list == null || list.size() == 0) {
			return "[]";
		}
		StringBuilder build = new StringBuilder();
		build.append("[");
		for (T item : list) {
			build.append("{");
			build.append(item.toString());
			build.append("},");
		}
		build.substring(0, build.length() - 1);
		build.append("]");
		return build.toString();
	}
	
	public static String getCartMapJsonString(Map<Product, Integer> cartMap) {
		if (cartMap == null) {
			return "\'productlist\':[]";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
			builder.append(buildJsonString(entry.getKey().toString() + ",\'num\':\'" + entry.getValue() + "\'"));
			builder.append(",");
		}
		builder.append("]");
		return "\'productlist\':" + builder.toString();
	}
	
	private JSONUtil() {
	}
}
