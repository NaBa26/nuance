package com.ecommerce.nuance.service;

import java.util.List;


import java.util.Optional;

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.model.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Optional<Book> getBookById(long bookId);
	
	public List<Book> getBookByGenre(String genre);
	
    public Book createBook(Book book);

    public Book updateBook(long bookId,Book updatedBook) throws BookNotFoundException;

    public void deleteBook(long bookId) throws BookNotFoundException;

	public Optional<Book> getBookByNameAndId(String bookName, long bookId);
	}
