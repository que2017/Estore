package com.duiyi.dao;

import java.util.List;

import com.duiyi.domain.Product;

public interface ProductDao {
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

}
