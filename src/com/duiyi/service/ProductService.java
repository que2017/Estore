package com.duiyi.service;

import java.util.List;

import com.duiyi.domain.Product;

public interface ProductService {
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
