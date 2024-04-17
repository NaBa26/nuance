package com.ecommerce.nuance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	 private UserService userService;

	    @Autowired
	    public UserController(UserService userService) {
	        this.userService = userService;
	    }
	    
	    @GetMapping
	    public List<User> getAllUsers() {
			return userService.getAllUsers();
		}

	    @GetMapping("/{userId}")
	    public Optional<User> getUserById(@PathVariable String userId) {
	        return this.userService.getUserById(Long.parseLong(userId));
	    }
	
		@PostMapping(consumes = "application/json")
	    public User createUser(@RequestBody User user) 
		{
			return this.userService.createUser(user);
	    }

        @PutMapping("/{userId}")
        public User updateUser(@PathVariable long userId, @RequestBody User updatedUser) throws UserNotFoundException 
        {
        	return this.userService.updateUser(userId, updatedUser);
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId) 
        {
        	try {
        		this.userService.deleteUser(Long.parseLong(userId));
        		return new ResponseEntity<>(HttpStatus.OK);
        	} catch (Exception e)
        	{
        		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        }

}
