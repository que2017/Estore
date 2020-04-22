package com.duiyi.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

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

}
