package com.duiyi.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

public class User implements Serializable {
	private int id;
	
	private String username;
	
	private String password;
	
	private String nickname;
	
	private String email;
	
	private String role;
	
	private int state;
	
	private String activecode;
	
	private Timestamp updatetime;
	
	public User() {
	}

	public User(Map<String, String[]> map) {
		this.username = map.get("username") != null ? map.get("username")[0] : "";
		this.password = map.get("password") != null ? map.get("password")[0] : "";
		this.nickname = map.get("nickname") != null ? map.get("nickname")[0] : "";
		this.email = map.get("email") != null ? map.get("email")[0] : "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getActivecode() {
		return activecode;
	}

	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
}
