package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.exception.OrderNotFoundException;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.model.Order;
import com.ecommerce.nuance.model.User;

public interface AdminService {
	
public List<Book> getAllBooks();
	
	public Optional<Book> getBookById(Long book_id);
	
    public Book createBook(Book book);

    public Book updateBook(Long bookId,Book updatedBook) throws BookNotFoundException;

    public void deleteBook(Long bookId);
    
    public List<Order> getAllOrders();

	public Optional<Order> getOrderById(Long order_id);
	
	public Order createOrder(Order order);

    public Order updateOrder(Long orderId,Order updatedOrder) throws OrderNotFoundException;

    public void deleteOrder(long orderId);

	public List<User> getAllUsers();

	User updateUser(long userId, User updatedUser) throws UserNotFoundException;

	void deleteUser(long long1);

	User createUser(User user);

	Optional<User> getUserById(Long long1);
	
}
