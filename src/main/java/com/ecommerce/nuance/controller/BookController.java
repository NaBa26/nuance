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

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.service.BookService;

@RestController 
@RequestMapping("/books")
public class BookController {

	@Autowired
	 private final BookService bookService;

	    @Autowired
	    public BookController(BookService bookService) {
	        this.bookService = bookService;
	    }

	    @GetMapping("/{bookId}")
	    public Optional<Book> getBookById(@PathVariable String bookId) {
	        return this.bookService.getBookById(Long.parseLong(bookId));
	    }
	
		@PostMapping(consumes = "application/json")
	    public Book createBook(@RequestBody Book book) 
		{
			return this.bookService.createBook(book);
	    }

        @PutMapping("/{bookId}")
        public Book updateBook(@PathVariable long bookId, @RequestBody Book updatedBook) throws BookNotFoundException 
        {
        	return this.bookService.updateBook(bookId, updatedBook);
        }

        @DeleteMapping("/{bookId}")
        public ResponseEntity<HttpStatus> deleteBook(String bookId) 
        {
        	try {
        		this.bookService.deleteBook(Long.parseLong(bookId));
        		return new ResponseEntity<>(HttpStatus.OK);
        	} catch (Exception e)
        	{
        		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        }
}
