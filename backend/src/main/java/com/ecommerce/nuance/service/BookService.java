package com.ecommerce.nuance.service;

import java.util.List;


import java.util.Optional;

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.model.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Book getBookById(Integer book_id);
	
    public Book createBook(Book book);

    public Book updateBook(Integer bookId,Book updatedBook) throws BookNotFoundException;

    public void deleteBook(Integer bookId);
	}
