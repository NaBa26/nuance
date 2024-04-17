package com.ecommerce.nuance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.exception.OrderNotFoundException;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.model.Order;
import com.ecommerce.nuance.model.User;

public class AdminServiceImpl {
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	private OrderServiceImpl orderServiceImpl;
	private UserServiceImpl userServiceImpl;
	
	public AdminServiceImpl(BookServiceImpl bookServiceImpl, OrderServiceImpl orderServiceImpl,
			UserServiceImpl userServiceImpl) {
		super();
		this.bookServiceImpl = bookServiceImpl;
		this.orderServiceImpl = orderServiceImpl;
		this.userServiceImpl = userServiceImpl;
	}
	
	public List<Book> getAllBooks()
	{
		return bookServiceImpl.getAllBooks();	
	}
	
	public Optional<Book> getBookById(Long bookId)
	{
		return bookServiceImpl.getBookById(bookId);
	}
	
	
	public Book createBook(Book book)
	{
		return bookServiceImpl.createBook(book);
	}
	

    public Book updateBook(Long bookId,Book updatedBook) throws BookNotFoundException 
    {
        
        return bookServiceImpl.updateBook(bookId, updatedBook);
    }

    @Transactional
    public void deleteBook(Long bookId)
    {
    	bookServiceImpl.deleteBook(bookId);
    }
    
    public List<Book> findByAuthorAndGenre(String author, String genre) {
        return bookServiceImpl.findByAuthorAndGenre(author, genre);
    }
    
    List<Book> findByPriceLessThanEqual(double price)
    {
    	return bookServiceImpl.findByPriceLessThanEqual(price);
    }
    Book findByIsbnIgnoreCase(String isbn) throws BookNotFoundException {
        return bookServiceImpl.findByIsbnIgnoreCase(isbn);
    }
    
    public List<Order> getAllOrders()
	{
		return orderServiceImpl.getAllOrders();
	}
	
	public Optional<Order> getOrderById(Long orderId)
	{
		return orderServiceImpl.getOrderById(orderId);
	}
	
	
	public Order createOrder(Order order)
	{
		return orderServiceImpl.createOrder(order);
	}
	

    public Order updateOrder(Long orderId,Order updatedOrder) throws OrderNotFoundException 
    {
        
        return orderServiceImpl.updateOrder(orderId, updatedOrder);
    }

    @Transactional
    public void deleteOrder(long orderId) 
    {
    	orderServiceImpl.deleteOrder(orderId);
    }
    
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate)
    {
		return orderServiceImpl.findByOrderDateBetween(startDate, endDate);
    }

    public List<User> getAllUsers()
	{
		return userServiceImpl.getAllUsers();
	}
	
	
	public Optional<User> getUserById(Long userId) //can't use long, use Long as ID is of generic type
	{
		return userServiceImpl.getUserById(userId);
	}
	
	
	public User createUser(User user)
	{
		return userServiceImpl.createUser(user);
	}
	

    public User updateUser(long userId,User updatedUser) throws UserNotFoundException 
    {
        
        return userServiceImpl.updateUser(userId, updatedUser);
    }

    @Transactional
    public void deleteUser(long userId) 
    {
		userServiceImpl.deleteUser(userId);
    }
    
    public List<User> findByCity(String city) {
        return userServiceImpl.findByCity(city);
    }
	
	

}
