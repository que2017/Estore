package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.domain.Product;

public interface ProductDao extends Dao {
	/**
	 * 添加商品
	 *
	 * @param product
	 */
	void addProduct(Product product);

	/**
	 * 查找所有商品
	 *
	 * @return
	 */
	List<Product> findAllProduct();

	/**
	 * 通过id查找商品
	 *
	 * @param id
	 * @return
	 */
	Product findProductById(String id);

	/**
	 * 减少商品的库存数量
	 *
	 * @param productId
	 * @param buynum
	 * @throws SQLException 
	 */
	void reducePnum(String productId, int buynum) throws SQLException;

}
