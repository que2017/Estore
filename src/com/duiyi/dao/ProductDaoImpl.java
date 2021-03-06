package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.duiyi.domain.Product;
import com.duiyi.utils.DaoUtil;

public class ProductDaoImpl implements ProductDao {

	public void addProduct(Product product) {
		String sql = "insert into products values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			runner.update(sql, product.getId(),
					product.getName(),
					product.getPrice(),
					product.getCategory(),
					product.getPnum(),
					product.getImgurl(),
					product.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Product> findAllProduct() {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			return runner.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Product findProductById(String id) {
		String sql = "select * from products where id=?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			return runner.query(sql, new BeanHandler<Product>(Product.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void reducePnum(String productId, int buynum) throws SQLException {
		String sql = "update products set pnum=pnum-? where id=? and pnum-?>=0";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		int count = runner.update(sql, buynum, productId, buynum);
		if (count <= 0) {
			throw new SQLException("��治�㣡");
		}
	}

	public void increasePnum(String productId, int buynum) {
		String sql = "update products set pnum=pnum+? where id=?";
		QueryRunner runner = new QueryRunner(DaoUtil.getSource());
		try {
			runner.update(sql, buynum, productId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
