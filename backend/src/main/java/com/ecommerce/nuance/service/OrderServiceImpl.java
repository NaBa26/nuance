package com.ecommerce.nuance.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ecommerce.nuance.model.Order;
import com.ecommerce.nuance.repository.CartRepository;
import com.ecommerce.nuance.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final CartRepository cartRepository;

	public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository)
	{
		this.orderRepository = orderRepository;
		this.cartRepository = cartRepository;
	}
	
	
	@Transactional
	public Order createOrder(Order order) {
	    if (order.getUserId() == null || order.getTotalAmount() == null) {
	        throw new IllegalArgumentException("User ID or total amount is invalid");
	    }
	    order.setOrderDate(LocalDateTime.now());
	    order.setCreatedAt(LocalDateTime.now());
	    order.setUpdatedAt(LocalDateTime.now());
	    
	    cartRepository.softDeleteCartItemsByUserId(order.getUserId());
	    
	    return orderRepository.save(order);
	}



}
