package com.duiyi.factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	
	private static Properties prop = null;
	
	static {
		try {
			prop = new Properties();
			prop.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private BasicFactory() {
	}
	
	public static BasicFactory getFactory() {
		return factory;
	}
	
	public <T> T getInstance(Class<T> clazz) {
		if (prop == null || clazz == null) {
			return null;
		}
		String className = prop.getProperty(clazz.getSimpleName());
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
