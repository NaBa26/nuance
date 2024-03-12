package com.ecommerce.nuance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.nuance.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
	List<Book> findByPriceLessThanEqual(double price);
	Book findByIsbnIgnoreCase(String isbn);
	List<Book> findByAuthorAndGenreIgnoreCase(String author, String genre);
	
}