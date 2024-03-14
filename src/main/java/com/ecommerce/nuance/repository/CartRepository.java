package com.ecommerce.nuance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.nuance.model.Cart;

public interface CartRepository extends JpaRepository<CartRepository, Long>  
{
	
}
