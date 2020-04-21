package com.duiyi.domain;

public class OrderItem {
	private String order_id;
	
	private String product_id;
	
	private int buynum;
	
	public OrderItem() {
	}
	
	public OrderItem(String orderId, String productId, int buyNum) {
		this.order_id = orderId;
		this.product_id = productId;
		this.buynum = buyNum;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
}
