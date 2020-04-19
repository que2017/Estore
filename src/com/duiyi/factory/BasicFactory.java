package com.duiyi.factory;

import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import com.duiyi.annotation.NeedTrans;
import com.duiyi.dao.Dao;
import com.duiyi.service.Service;
import com.duiyi.utils.DaoUtil;

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

	
	@SuppressWarnings("unchecked")
	public <T extends Service> T getService(Class<T> clazz) {
		if (prop == null || clazz == null) {
			return null;
		}
		String className = prop.getProperty(clazz.getSimpleName());
		try {
			final T service = (T) Class.forName(className).newInstance();
			// ʹ�ö�̬��������ע���ҳ���Ҫ���ݿ�����ķ���
			T proxy = (T) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(),
				new InvocationHandler() {
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if (method.isAnnotationPresent(NeedTrans.class)) {
						// ������NeedTransע��
						try {
							// ��������
							DaoUtil.startTrans();
							// ����ִ�з���
							Object obj = method.invoke(service, args);
							// �ύ����
							DaoUtil.commit();
							return obj;
						} catch (InvocationTargetException e) {
							// method�����׳����쳣���ع�����
							DaoUtil.rollback();
							e.printStackTrace();
							throw new RuntimeException(e.getTargetException());
						} catch (Exception e) {
							// �����쳣���ع�����
							DaoUtil.rollback();
							throw new RuntimeException(e);
						} finally {
							DaoUtil.release();
						}
					} else {
						// ������NeedTransע��
						return method.invoke(service, args);
					}
				}
			});
			return proxy;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public <T extends Dao> T getDao(Class<T> clazz) {
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
	
//	public <T> T getInstance(Class<T> clazz) {
//		if (prop == null || clazz == null) {
//			return null;
//		}
//		String className = prop.getProperty(clazz.getSimpleName());
//		try {
//			return (T) Class.forName(className).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
}
