package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.duiyi.domain.Order;
import com.duiyi.domain.SaleInfo;
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

	public Order findOrderById(String orderId) {
		String sql = "select * from orders where id=?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			return runner.query(sql, new BeanHandler<Order>(Order.class), orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void changeOrderState(String orderId, int state) {
		String sql = "update orders set paystate=? where id=?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			runner.update(sql, state, orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<SaleInfo> getSaleList() {
		// 多表查询，查询orders、products、orderitem表找出商品id、商品名称、商品种类、销售数量
		String sql = "select products.id productName, products.name productName, products.category category, sum(orderitem.buynum) saleNum "
			+ "from orders, products, orderitem "
			+ "where orders.paystate=1 and products.id=orderitem.product_id and orders.id=orderitem.order_id "
			+ "group by products.id "
			+ "order by saleNum desc";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			return runner.query(sql, new BeanListHandler<SaleInfo>(SaleInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
