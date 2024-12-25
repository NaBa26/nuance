package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.nuance.dto.LoginResult;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;

public interface UserService {
	
	public List<User> getAllUsers();

	void deleteUser(Long long1);

	User createUser(User user);

	Optional<User> getUserById(Long long1);

	User updateUser(Long userId, User updatedUser) throws UserNotFoundException;

	LoginResult verifyCredentials(String username, String password);

	public void updatePassword(String recipient, String tempPassword);

}
