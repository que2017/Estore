package com.duiyi.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݿ⴦����ع����࣬�������ݿ�����Ĵ���
 */
public class DaoUtil {
	private static DataSource source = new ComboPooledDataSource();
	
	private static ThreadLocal<Connection> connLocal = new ThreadLocal<Connection>();
	
	public static void startTrans() {
		
	}
	
	public static void commit() {
		
	}
	
	public static void rollback() {
		
	}
	
	public static void release() {
		
	}
	
	public static DataSource getSource(){
		return source;
	}
	
	public static Connection getConnection() {
		try {
			return source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private DaoUtil() {
	}
}
