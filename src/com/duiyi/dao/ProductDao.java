package com.duiyi.dao;

import java.sql.SQLException;
import java.util.List;

import com.duiyi.domain.Product;

public interface ProductDao extends Dao {
	/**
	 * �����Ʒ
	 *
	 * @param product
	 */
	void addProduct(Product product);

	/**
	 * ����������Ʒ
	 *
	 * @return
	 */
	List<Product> findAllProduct();

	/**
	 * ͨ��id������Ʒ
	 *
	 * @param id
	 * @return
	 */
	Product findProductById(String id);

	/**
	 * ������Ʒ�Ŀ������
	 *
	 * @param productId
	 * @param buynum
	 * @throws SQLException 
	 */
	void reducePnum(String productId, int buynum) throws SQLException;

}
