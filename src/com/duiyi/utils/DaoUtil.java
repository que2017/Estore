package com.duiyi.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݿ⴦����ع����࣬�������ݿ�����Ĵ���
 */
public class DaoUtil {
	private static DataSource source = new ComboPooledDataSource();
	
	/**
	 * �������ݿ����ӣ���Ϊ�ֲ߳̾�����������ǰ�߳̿ɼ���
	 */
	private static ThreadLocal<Connection> proxyConnLocal = new ThreadLocal<Connection>();

	/**
	 * ��ʵ���ݿ����ӣ���Ϊ�ֲ߳̾�����������ǰ�߳̿ɼ�������������ʱ�ر�����ʹ�á�
	 */
	private static ThreadLocal<Connection> realConnLocal = new ThreadLocal<Connection>();
	
	/**
	 * ��������
	 * @throws SQLException 
	 */
	public static void startTrans() throws SQLException {
		final Connection conn = source.getConnection();
		conn.setAutoCommit(false);
		// ������ʵ���ݿ����ӵ��̱߳���
		realConnLocal.set(conn);
		// ����������ݿ����ӣ�Ϊ�˽��ÿ��sqlִ�к󶼻��Զ��ر����ݿ����ӵ����⣬��дclose����
		Connection proxyConn = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces(),
			new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if ("close".equals(method.getName())) {
					return null;
				} else {
					return method.invoke(conn, args);
				}
			}
		});
		// ����������ݿ����ӵ��̱߳���
		proxyConnLocal.set(proxyConn);
	}
	
	/**
	 * �ύ����
	 */
	public static void commit() {
		DbUtils.commitAndCloseQuietly(proxyConnLocal.get());
	}

	/**
	 * �ع�����
	 */
	public static void rollback() {
		DbUtils.rollbackAndCloseQuietly(proxyConnLocal.get());
	}
	
	/**
	 * �ͷ���Դ
	 */
	public static void release() {
		DbUtils.closeQuietly(realConnLocal.get()); // �ر���Ϊ����û�رյ���ʵ����
		realConnLocal.remove();
		proxyConnLocal.remove();
	}
	
	/**
	 * ��ȡ���ݿ���Դ
	 * 		���߳��п��������񣺷���һ�������getConnection����������Դ��������������ÿ��getConection������ͬһ�������������Connection
	 * 		���߳���û�п������񣺷�����ͨ�����ݿ���Դ
	 * @return
	 */
	public static DataSource getSource() {
		if (realConnLocal.get() != null) {
			// ���������񣬶�̬����source��getConnection���������䷵��proxyConnLocal.get()
			DataSource proxy = (DataSource) Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(),
				new InvocationHandler() {
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if ("getConnection".equals(method.getName())) {
						return proxyConnLocal.get();
					} else {
						return method.invoke(source, args);
					}
				}
			});
			return proxy;
		} else {
			// û�п�������
			return source;
		}
	}
	
	private DaoUtil() {
	}
}
