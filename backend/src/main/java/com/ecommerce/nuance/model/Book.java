package com.ecommerce.nuance.model;

import java.awt.Image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private long bookId;
	@Column(name="title")
	private String title;
	@Column(name="author")
	private String author;
	@Column(name="genre")
	private String genre;
	@Column(name="isbn")
	private String isbn;
	@Column(name="price")
	private double price;

	@Transient 
	private Image image;
	
	public Book() {
		super();
	}
	
	public long getBook_id() {
		return bookId;
	}
	public void setBook_id(long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public Book(long bookId, String title, String author, String genre, String isbn, double price) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.isbn = isbn;
		this.price = price;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", "
				+ "Author=" + author + ", Genre=" + genre + ", "
						+ "isbn=" + isbn + ", price=" + price + "]";
	}
	
}
