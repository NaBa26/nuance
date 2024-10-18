package com.ecommerce.nuance.service;

import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

//    @Override
//    public Optional<User> getUserById(Long userId) {
//        return userRepository.findById(userId);
//    }

	@Override
	public User createUser(User user) {
		if (user != null) {
			return userRepository.save(user);
		}
		return null;
	}

//    @Override
//    public User updateUser(Long userId, User updatedUser) throws UserNotFoundException {
//        return userRepository.findById(userId).map(user -> {
//            user.setCity(updatedUser.getCity());
//            user.setEmail(updatedUser.getEmail());
//            user.setPassword(updatedUser.getPassword());
//            user.setUsername(updatedUser.getUsername());
//            return userRepository.save(user);
//        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
//    }

	@Override
	public User updateUser(long userId, User updatedUser) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(long long1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<User> getUserById(Long long1) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public User updateUser(Long userId, User updatedUser) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
