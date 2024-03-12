package com.ecommerce.nuance.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;

public interface UserService {

	User updateUser(long userId, User updatedUser) throws UserNotFoundException;

	void deleteUser(long long1);

	User createUser(User user);

	Optional<User> getUserById(Long long1);

}
