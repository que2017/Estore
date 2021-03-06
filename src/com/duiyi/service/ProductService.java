package com.duiyi.service;

import java.util.List;

import com.duiyi.domain.Product;

public interface ProductService extends Service {
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

}
