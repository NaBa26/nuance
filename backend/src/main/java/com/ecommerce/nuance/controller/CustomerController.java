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
import com.ecommerce.nuance.model.Customer;
import com.ecommerce.nuance.service.CustomerService;

@RestController
@RequestMapping("/users")
public class CustomerController {

	@Autowired
	 private CustomerService userService;

	    @Autowired
	    public CustomerController(CustomerService userService) {
	        this.userService = userService;
	    }
	    
	    @GetMapping
	    public List<Customer> getAllUsers() {
			return userService.getAllUsers();
		}

	    @GetMapping("/{userId}")
	    public Optional<Customer> getUserById(@PathVariable String userId) {
	        return this.userService.getUserById(Long.parseLong(userId));
	    }
	
		@PostMapping(consumes = "application/json")
	    public Customer createUser(@RequestBody Customer user) 
		{
			return this.userService.createUser(user);
	    }

        @PutMapping("/{userId}")
        public Customer updateUser(@PathVariable long userId, @RequestBody Customer updatedUser) throws UserNotFoundException 
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
