package com.duiyi.service;

import java.util.List;

import com.duiyi.domain.Product;

public interface ProductService {
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

}
