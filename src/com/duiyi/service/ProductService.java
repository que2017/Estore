package com.duiyi.service;

import java.util.List;

import com.duiyi.domain.Product;

public interface ProductService extends Service {
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

}
