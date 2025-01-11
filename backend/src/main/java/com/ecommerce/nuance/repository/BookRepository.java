package com.ecommerce.nuance.repository;

import java.util.List;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.nuance.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
	List<Book> findByGenre(String genre);
	Optional<Book> findById(Integer id);
	List<Book> findByPriceLessThanEqual(double price);
	List<Book> findByAuthorAndGenreIgnoreCase(String author, String genre);
	Optional<Book> findByIdAndName(Long id, String bookName);
}