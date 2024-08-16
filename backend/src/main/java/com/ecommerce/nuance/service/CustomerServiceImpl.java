package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.Customer;
import com.ecommerce.nuance.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository userRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository userRepository) // This constructor is created to automatically update the value in the repository class
	{
		this.userRepository = userRepository;
	}
	
	public List<Customer> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	
	public Optional<Customer> getUserById(Long userId) //can't use long, use Long as ID is of generic type
	{
		return userRepository.findById(userId);
	}
	
	
	public Customer createUser(Customer user)
	{
		if(user!=null)
		{
		return userRepository.save(user);
		}
		return null;
	}
	

    public Customer updateUser(long userId,Customer updatedUser) throws UserNotFoundException 
    {
        
        return userRepository.findById(userId).map(user->{ 
        	user.setCity(updatedUser.getCity());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setUsername(updatedUser.getUsername());
            
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Transactional
    public void deleteUser(long userId) 
    {
		userRepository.deleteById(userId);
    }
    
    public List<Customer> findByCity(String city) {
        return userRepository.findByCity(city);
    }

}
