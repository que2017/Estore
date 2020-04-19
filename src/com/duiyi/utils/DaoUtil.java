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
 * 数据库处理相关工具类，包含数据库事务的处理
 */
public class DaoUtil {
	private static DataSource source = new ComboPooledDataSource();
	
	/**
	 * 代理数据库连接，此为线程局部变量，仅当前线程可见。
	 */
	private static ThreadLocal<Connection> proxyConnLocal = new ThreadLocal<Connection>();

	/**
	 * 真实数据库连接，此为线程局部变量，仅当前线程可见。当开启事务时关闭连接使用。
	 */
	private static ThreadLocal<Connection> realConnLocal = new ThreadLocal<Connection>();
	
	/**
	 * 开启事务
	 * @throws SQLException 
	 */
	public static void startTrans() throws SQLException {
		final Connection conn = source.getConnection();
		conn.setAutoCommit(false);
		// 保存真实数据库连接到线程本地
		realConnLocal.set(conn);
		// 构造代理数据库连接，为了解决每条sql执行后都会自动关闭数据库连接的问题，重写close方法
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
		// 保存代理数据库连接到线程本地
		proxyConnLocal.set(proxyConn);
	}
	
	/**
	 * 提交事务
	 */
	public static void commit() {
		DbUtils.commitAndCloseQuietly(proxyConnLocal.get());
	}

	/**
	 * 回滚事务
	 */
	public static void rollback() {
		DbUtils.rollbackAndCloseQuietly(proxyConnLocal.get());
	}
	
	/**
	 * 释放资源
	 */
	public static void release() {
		DbUtils.closeQuietly(realConnLocal.get()); // 关闭因为事务没关闭的真实连接
		realConnLocal.remove();
		proxyConnLocal.remove();
	}
	
	/**
	 * 获取数据库资源
	 * 		当线程中开启了事务：返回一个改造过getConnection方法的数据源，这个方法改造后每次getConection都返回同一个开启过事务的Connection
	 * 		当线程中没有开启事务：返回普通的数据库资源
	 * @return
	 */
	public static DataSource getSource() {
		if (realConnLocal.get() != null) {
			// 开启过事务，动态代理source的getConnection方法，让其返回proxyConnLocal.get()
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
			// 没有开启事务
			return source;
		}
	}
	
	private DaoUtil() {
	}
}
