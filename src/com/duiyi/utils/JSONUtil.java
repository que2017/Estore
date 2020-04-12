package com.duiyi.utils;

import java.util.List;

public class JSONUtil {
	public static String buildJsonString(String string) {
		return "{" + string + "}";
	}
	
	public static <T> String buildJsonArrayString(List<T> list) {
		if (list == null || list.size() == 0) {
			return "";
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
	
	private JSONUtil() {
	}
}
