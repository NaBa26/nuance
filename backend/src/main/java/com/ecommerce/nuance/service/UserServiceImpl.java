package com.ecommerce.nuance.service;

import com.ecommerce.nuance.dto.LoginResult;
import com.ecommerce.nuance.exception.UserNotFoundException;

import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

//    @Override
//    public Optional<User> getUserById(Long userId) {
//        return userRepository.findById(userId);
//    }

	public User createUser(User user) { 
		if (user != null) {
			user.setPassword(encoder.encode(user.getPassword())); //password encoding
			return userRepository.save(user);
		}
		return null;
	}

//    public User updateUser(Long userId, User updatedUser) throws UserNotFoundException {
//        return userRepository.findById(userId).map(user -> {
//            user.setCity(updatedUser.getCity());
//            user.setEmail(updatedUser.getEmail());
//            user.setPassword(updatedUser.getPassword());
//            user.setUsername(updatedUser.getUsername());
//            return userRepository.save(user);
//        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
//    }

	public void deleteUser(long long1) {
		// TODO Auto-generated method stub

	}

	public Optional<User> getUserById(Long long1) {
		// TODO Auto-generated method stub
		return Optional.empty();
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
	public User updateUser(Long userId, User updatedUser) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long long1) {
		// TODO Auto-generated method stub
		
	}
}
