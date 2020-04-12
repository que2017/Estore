package com.duiyi.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

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

}
