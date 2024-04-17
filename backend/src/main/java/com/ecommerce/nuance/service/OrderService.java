package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.nuance.exception.OrderNotFoundException;
import com.ecommerce.nuance.model.Order;


public interface OrderService {
	
	public List<Order> getAllOrders();

	public Optional<Order> getOrderById(Long order_id);
	
	public Order createOrder(Order order);

    public Order updateOrder(Long orderId,Order updatedOrder) throws OrderNotFoundException;

    public void deleteOrder(long orderId);
    
}
