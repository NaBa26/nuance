package com.ecommerce.nuance.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.nuance.exception.OrderNotFound;
import com.ecommerce.nuance.model.Order;
import com.ecommerce.nuance.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) // This constructor is created to automatically update the value in the repository class
	{
		this.orderRepository = orderRepository;
	}
	
	public Optional<Order> getOrderById(Long orderId)
	{
		return getOrderById(orderId);
	}
	
	
	public Order createOrder(Order order)
	{
		return orderRepository.save(order);
	}
	

    public Order updateOrder(Long orderId,Order updatedOrder) throws OrderNotFound 
    {
        
        return orderRepository.findById(orderId).map(order->{ 
        	order.setStatus(updatedOrder.getStatus());
            order.setTotalAmount(updatedOrder.getTotalAmount());
            order.setOrderDate(updatedOrder.getOrderDate());
            return orderRepository.save(order);
        }).orElse(null);
    }

    @Transactional
    public void deleteOrder(long orderId) 
    {
    	orderRepository.deleteById(orderId);
    }
    
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate)
    {
		return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

}
