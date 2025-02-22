package com.ecommerce.nuance.service;

import com.ecommerce.nuance.dto.LoginResult;

import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	public UserServiceImpl(UserRepository userRepository,
			BCryptPasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	@Override
	public Optional<User> getUser(long userId) {
		
		return userRepository.findById(userId);
	}

	public User createUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public LoginResult verifyCredentials(String username, String password) {
		Optional<User> userOpt = userRepository.findByUsername(username);
		if (userOpt.isEmpty()) {
			return new LoginResult(Optional.empty(), "User not found");
		}

		User user = userOpt.get();
		if (!encoder.matches(password, user.getPassword())) {
			return new LoginResult(userOpt, "Wrong password");
		}

		return new LoginResult(userOpt, "Success");
	}

	@Override
	@Transactional
	public void updatePassword(String recipient, String tempPassword) {
		User existingUser = userRepository.findByEmail(recipient).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));
		existingUser.setPassword(encoder.encode(tempPassword));
		userRepository.save(existingUser);
	}

	@Override
	public User updateUser(long userId, User updatedUser)
			throws UserNotFoundException {
		return userRepository.findById(userId).map(existingUser -> {
			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setCity(updatedUser.getCity());
			existingUser.setPassword(encoder.encode(updatedUser.getPassword()));
			return userRepository.save(existingUser);
		}).orElseThrow(() -> new UserNotFoundException("User not found"));
	}
}
