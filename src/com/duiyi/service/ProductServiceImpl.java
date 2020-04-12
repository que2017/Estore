package com.duiyi.service;

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

}
