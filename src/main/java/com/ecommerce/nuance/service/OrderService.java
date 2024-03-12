package com.ecommerce.nuance.service;

import java.util.Optional;

import com.ecommerce.nuance.exception.OrderNotFound;
import com.ecommerce.nuance.model.Order;


public interface OrderService {

	public Optional<Order> getOrderById(Long order_id);
	
	public Order createOrder(Order order);

    public Order updateOrder(Long orderId,Order updatedOrder) throws OrderNotFound;

    public void deleteOrder(long orderId);
    
}
