package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

	public List<Order> findOrdersByUserId(int id) {
		String sql = "select * from orders where user_id=?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			return runner.query(sql, new BeanListHandler<Order>(Order.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void deleteOrderById(String orderId) {
		String sql = "delete from orders where id=?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			runner.update(sql, orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
