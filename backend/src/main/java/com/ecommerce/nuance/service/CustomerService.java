package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.Customer;

public interface CustomerService {
	
	public List<Customer> getAllUsers();

	Customer updateUser(long userId, Customer updatedUser) throws UserNotFoundException;

	void deleteUser(long long1);

	Customer createUser(Customer user);

	Optional<Customer> getUserById(Long long1);

}
