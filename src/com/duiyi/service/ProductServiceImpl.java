package com.duiyi.service;

import java.util.List;
import java.util.UUID;

import com.duiyi.dao.ProductDao;
import com.duiyi.domain.Product;
import com.duiyi.factory.BasicFactory;

public class ProductServiceImpl implements ProductService {
	private ProductDao dao = BasicFactory.getFactory().getInstance(ProductDao.class);

	public void addProduct(Product product) {
		product.setId(UUID.randomUUID().toString());
		dao.addProduct(product);
	}

	public List<Product> findAllProduct() {
		return dao.findAllProduct();
	}

	public Product findProductById(String id) {
		return dao.findProductById(id);
	}

}
