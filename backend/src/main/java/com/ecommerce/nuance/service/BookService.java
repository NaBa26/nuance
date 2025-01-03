package com.ecommerce.nuance.service;

import java.util.List;


import java.util.Optional;

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.model.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Optional<Book> getBookById(Integer book_id);
	
	public List<Book> getBookByGenre(String genre);
	
    public Book createBook(Book book);

    public Book updateBook(Integer bookId,Book updatedBook) throws BookNotFoundException;

    public void deleteBook(Integer bookId);

	public Optional<Book> getBookByNameAndId(String bookName, Long id);
	}
