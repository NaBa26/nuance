package com.ecommerce.nuance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.nuance.model.User;

@Repository
public  interface UserRepository extends JpaRepository<User,Long>{

	List<User> findByCity(String city);

	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findById(long id);

}
