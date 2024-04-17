package com.ecommerce.nuance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_Id")
	private long userId;
	@Column(name="username")
    private String username;
	@Column(name="password")
    private String password;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "Admin [userId=" + userId + ", username=" + username + 
				", password=" + password + "]";
	}
	

}
