package com.duiyi.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.duiyi.domain.Order;
import com.duiyi.utils.DaoUtil;

public class OrderDaoImpl implements OrderDao {

	public void addOrder(Order order) throws SQLException {
		String sql = "insert into orders values (?,?,?,?,null,?)";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		runner.update(sql, order.getId(),
				order.getMoney(),
				order.getReceiverinfo(),
				order.getPaystate(),
				order.getUser_id());
	}

}
