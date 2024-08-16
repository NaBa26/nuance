package com.ecommerce.nuance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_Id")
	private long userId;
	@Column(name="username")
    private String username;
	@Column(name="password")
    private String password;
	@Column(name="email")
    private String email;
	@Column(name="city")
    private String city;
	
	public Customer(long userId, String username, String password, String email, String city) { //This is only need if the variables defined are private
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.city = city;
	}
	
	public long getUser_id() {
		return userId;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
