package com.ecommerce.nuance.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.service.BookService;

@RestController 
@RequestMapping("/books")
public class BookController {

	@Autowired
	private final BookService bookService;
	
		//instantiating using a constructor for dependency injection and loose coupling
	    public BookController(BookService bookService) { 
	        this.bookService = bookService;
	    }
	    
	    @GetMapping()
	    public List<Book> getAllBooks() {
			return bookService.getAllBooks();
		}

	    @GetMapping("/{bookName}")
	    public Book getBookById(@PathVariable String bookName, @RequestParam("id") String bookId) {
	        return this.bookService.getBookById(Integer.parseInt(bookId));
	    }
}
