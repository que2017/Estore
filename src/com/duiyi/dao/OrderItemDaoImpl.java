package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.duiyi.domain.OrderItem;
import com.duiyi.utils.DaoUtil;

public class OrderItemDaoImpl implements OrderItemDao {

	public void addOrderItem(OrderItem item) throws SQLException {
		String sql = "insert into orderitem values (?,?,?)";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		runner.update(sql, item.getOrder_id(),
				item.getProduct_id(),
				item.getBuynum());
	}

	public List<OrderItem> findProductsByOrderId(String id) {
		String sql = "select * from orderitem where order_id=?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
