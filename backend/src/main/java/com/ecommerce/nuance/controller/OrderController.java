package com.ecommerce.nuance.controller;

import java.math.BigDecimal;
import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.nuance.model.Order;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.UserRepository;
import com.ecommerce.nuance.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	 private final OrderService orderService;
	private final UserRepository userRepository;

	    public OrderController(OrderService orderService, UserRepository userRepository) {
	        this.orderService = orderService;
	        this.userRepository = userRepository;
	    }
	    
	    @PostMapping("/create-order")
	    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
	    	System.out.println(order.toString());
	        userRepository.findById(order.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

	        Order savedOrder = orderService.createOrder(order);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
	    }



	
}
