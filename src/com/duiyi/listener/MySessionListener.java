package com.duiyi.listener;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.duiyi.domain.Product;
import com.duiyi.utils.Constants;

public class MySessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setAttribute(Constants.CART_MAP, new LinkedHashMap<Product, Integer>());
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
	}

}
