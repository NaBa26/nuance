package com.ecommerce.nuance.model;


import com.fasterxml.jackson.annotation.JsonProperty;

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
	private long id;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
    private String last_name;
	
    private String email;
    private String username;
    private String password;
    
    @Column(name="city")
    private String city; 

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getfirstName() {
		return first_name;
	}

	public void setfirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getlastName() {
		return last_name;
	}

	public void setlastName(String last_name) {
		this.last_name = last_name;
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
	
	public User(){}

	public User(long id, String first_name, String last_name, String email, String username, String password,
			String city) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.city = city;
	}
	
	
	
}
