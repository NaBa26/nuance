package com.ecommerce.nuance.service;

import com.ecommerce.nuance.dto.LoginResult;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;

public interface UserService {

	User createUser(User user);

	LoginResult verifyCredentials(String username, String password);

	public void updatePassword(String recipient, String tempPassword);

	User updateUser(Long userId, User updatedUser) throws UserNotFoundException;

}
