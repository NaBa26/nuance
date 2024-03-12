package com.ecommerce.nuance.controller;

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

import com.ecommerce.nuance.exception.OrderNotFound;
import com.ecommerce.nuance.model.Order;
import com.ecommerce.nuance.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	 private final OrderService orderService;

	    @Autowired
	    public OrderController(OrderService orderService) {
	        this.orderService = orderService;
	    }

	    @GetMapping("/{orderId}")
	    public Optional<Order> getOrderById(@PathVariable String orderId) {
	        return this.orderService.getOrderById(Long.parseLong(orderId));
	    }
	
		@PostMapping(consumes = "application/json")
	    public Order createOrder(@RequestBody Order order) 
		{
			return this.orderService.createOrder(order);
	    }

       @PutMapping("/{orderId}")
       public Order updateOrder(@PathVariable long orderId, @RequestBody Order updatedOrder) throws OrderNotFound 
       {
       	return this.orderService.updateOrder(orderId, updatedOrder);
       }

       @DeleteMapping("/{orderId}")
       public ResponseEntity<HttpStatus> deleteOrder(@PathVariable String orderId) 
       {
       	try {
       		this.orderService.deleteOrder(Long.parseLong(orderId));
       		return new ResponseEntity<>(HttpStatus.OK);
       	} catch (Exception e)
       	{
       		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       	}
       }
	
}
