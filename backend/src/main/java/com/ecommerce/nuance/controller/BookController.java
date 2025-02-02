package com.ecommerce.nuance.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.service.BookService;

@RestController 
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private final BookService bookService;
	
		//instantiating using a constructor for dependency injection and loose coupling
	    public BookController(BookService bookService) { 
	        this.bookService = bookService;
	    }
	    
	    @GetMapping()
	    public ResponseEntity<List<Book>> getBooks() {
	        List<Book> books;
	        books = bookService.getAllBooks();
	        return ResponseEntity.ok(books);
	    }



	    @GetMapping("/{bookName}")
	    public ResponseEntity<Book> getBookById(@PathVariable String bookName, @RequestParam("id") String bookId) {
	        try {
	            Optional<Book> bookOptional = bookService.getBookById(Integer.parseInt(bookId));
	            
	            if (bookOptional.isPresent()) {
	                return ResponseEntity.ok(bookOptional.get());
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.badRequest().build();
	        }
	    }

}
