package com.ecommerce.nuance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {

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
	public long getUser_id() {
		return userId;
	}
	
	public User() {
		super();
	}
	
	public void setUser_id(int userId) {
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", city=" + city + "]";
	}
}
