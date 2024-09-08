//The variable named here will be used in the Repository class query methods, so make sure that they align with one another.
package com.ecommerce.nuance.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {

	
	/*The @GeneratedValue annotation in JPA is used to specify the strategy used to generate the primary values for entities. 
	The GenerationType.IDENTITY strategy is commonly used with databases that supports auto incrementing*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String name;
	private String image;
	private String author;
	private String isbn;
	private float price;
	private int quantity;
	private String genre;
	@Column(name = "published_date", columnDefinition = "DATE")
	private LocalDate published_date;
	
	public Book(int id, String name, String image, String author, String isbn, float price, int quantity, String genre, LocalDate published_date) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.quantity = quantity;
		this.genre = genre;
		this.published_date = published_date;
	}
	
	/*
	 * The no-argument constructor is essential for the framework to instantiate this entity class
	 * using reflection. It allows the creation of proxy instances, which are subclasses that override
	 * certain methods to implement lazy-loading behavior. This constructor is necessary because,
	 * at the time of instantiation, the framework may not yet have access to the entity's data,
	 * and therefore cannot use a parameterized constructor. By providing a no-argument constructor,
	 * we ensure that the framework can create an empty instance of this class, which can later be
	 * populated with data as needed.
	 */
	public Book() {}

    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public LocalDate getPublishedDate() {
		return published_date;
	}
	public void setPublishedDate(LocalDate published_date) {
		this.published_date = published_date;
	}
	
	
}
