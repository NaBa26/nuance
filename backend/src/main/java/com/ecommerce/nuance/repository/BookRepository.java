package com.ecommerce.nuance.repository;

import java.util.List;


import java.util.Optional;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.nuance.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
	List<Book> findByGenre(String genre);
	Optional<Book> findById(long id);
	List<Book> findByPriceLessThanEqual(double price);
	List<Book> findByAuthorAndGenreIgnoreCase(String author, String genre);
	Optional<Book> findByIdAndName(long id, String bookName);
}