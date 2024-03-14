package com.ecommerce.nuance.controller;

import java.util.List;
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

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.exception.OrderNotFoundException;
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.model.Order;
import com.ecommerce.nuance.model.User;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private OrderController orderController;

	private UserController userController;

	private BookController bookController;

	@Autowired
	public AdminController(OrderController orderController, UserController userController,
			BookController bookController) {
		super();
		this.orderController = orderController;
		this.userController = userController;
		this.bookController = bookController;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks()
	{
		return ResponseEntity.ok(bookController.getAllBooks());
	}

	@GetMapping("/{bookId}")
    public Optional<Book> getBookById(@PathVariable String bookId) {
        return bookController.getBookById(bookId);
    }

	@PostMapping(consumes = "application/json")
    public Book createBook(@RequestBody Book book) 
	{
		return bookController.createBook(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable long bookId, @RequestBody Book updatedBook) throws BookNotFoundException 
    {
    	return bookController.updateBook(bookId, updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String bookId) 
    {
    	try {
    		bookController.deleteBook(bookId);
    		return new ResponseEntity<>(HttpStatus.OK);
    	} catch (Exception e)
    	{
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
	
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders()
	{
		return ResponseEntity.ok(orderController.getAllOrders());
	}
	
	@GetMapping("/{orderId}")
    public Optional<Order> getOrderById(@PathVariable String orderId) {
        return orderController.getOrderById(orderId);
    }

	@PostMapping(consumes = "application/json")
    public Order createOrder(@RequestBody Order order) 
	{
		return orderController.createOrder(order);
    }

   @PutMapping("/{orderId}")
   public Order updateOrder(@PathVariable long orderId, @RequestBody Order updatedOrder) throws OrderNotFoundException 
   {
   	return orderController.updateOrder(orderId, updatedOrder);
   }

   @DeleteMapping("/{orderId}")
   public ResponseEntity<HttpStatus> deleteOrder(@PathVariable String orderId) 
   {
   	try {
   		orderController.deleteOrder(orderId);
   		return new ResponseEntity<>(HttpStatus.OK);
   	} catch (Exception e)
   	{
   		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   	}
   }
   
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers()
	{
		return ResponseEntity.ok(userController.getAllUsers());
	}
	
	@GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable String userId) {
        return userController.getUserById(userId);
    }

	@PostMapping(consumes = "application/json")
    public User createUser(@RequestBody User user) 
	{
		return userController.createUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody User updatedUser) throws UserNotFoundException 
    {
    	return userController.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId) 
    {
    	try {
    		userController.deleteUser(userId);
    		return new ResponseEntity<>(HttpStatus.OK);
    	} catch (Exception e)
    	{
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }


	
	
}
