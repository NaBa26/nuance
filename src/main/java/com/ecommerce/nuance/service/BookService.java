package com.ecommerce.nuance.service;

import java.util.Optional;

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.model.Book;

public interface BookService {
	public Optional<Book> getBookById(Long book_id);
	
    public Book createBook(Book book);

    public Book updateBook(Long bookId,Book updatedBook) throws BookNotFoundException;

    public void deleteBook(Long bookId);
	}
