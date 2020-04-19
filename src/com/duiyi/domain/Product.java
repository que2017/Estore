package com.duiyi.domain;

import java.io.Serializable;
import java.util.Map;

public class Product implements Serializable {
	private String id;
	
	private String name;
	
	private double price;
	
	private String category;
	
	private int pnum;
	
	private String imgurl;
	
	private String description;
	
	public Product() {
	}

	public Product(Map<String, String> paramMap) {
		this.id = paramMap.get("id");
		this.name = paramMap.get("name");
		this.price = Double.parseDouble(paramMap.get("price"));
		this.category = paramMap.get("category");
		this.pnum = Integer.parseInt(paramMap.get("pnum"));
		this.imgurl = paramMap.get("imgurl");
		this.description = paramMap.get("description");
	}

	@Override
	public String toString() {
		return "\'id\':\'" + id
			+ "\',\'name\':\'" + name
			+ "\',\'price\':\'" + price
			+ "\',\'category\':\'" + category
			+ "\',\'pnum\':\'" + pnum
			+ "\',\'imgurl\':\'" + imgurl
			+ "\',\'description\':\'" + description + "\'";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Product other = (Product) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
