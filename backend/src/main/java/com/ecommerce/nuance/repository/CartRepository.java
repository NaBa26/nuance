package com.ecommerce.nuance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.nuance.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>  
{
	void deleteAll();
}
