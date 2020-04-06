package com.duiyi.domain;

import java.io.Serializable;

public class ResultCodeData implements Serializable {
	private String result;
	
	private int code;
	
	public ResultCodeData() {
	}
	
	public ResultCodeData(String result, int code) {
		this.result = result;
		this.code = code;
	}

	@Override
	public String toString() {
		return "\'result\':\'" + result + "\',"
				+ "\'code\':\'" + code + "\'";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
