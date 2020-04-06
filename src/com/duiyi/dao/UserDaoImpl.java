package com.duiyi.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.duiyi.domain.User;
import com.duiyi.utils.DaoUtil;

public class UserDaoImpl implements UserDao {
	public User findUser(String tagName, String tagValue) {
		String sql = "select * from users where " + tagName + " = ?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), tagValue);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void addUser(User user, Connection conn) {
		String sql = "insert into users values(null, ?, ?, ?, ?, ?, ?, ?, null)";
		QueryRunner runner = new QueryRunner();
		try {
			runner.update(conn, sql, user.getUsername(),
					user.getPassword(),
					user.getNickname(),
					user.getEmail(),
					user.getRole(),
					user.getState(),
					user.getActivecode());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
