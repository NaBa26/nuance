//The variable named here will be used in the Repository class query methods, so make sure that they align with one another.
package com.ecommerce.nuance.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	private Long id;
	private String name;
	private String image;
	private String author;
	private String isbn;
	private BigDecimal price;
	private Integer quantity;
	private String genre;
	
	@Column(name="date_published")
	private LocalDate datePublished;
	
	@CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
	
	public Book(Long id, String name, String image, String author, String isbn, BigDecimal price, Integer quantity, String genre, LocalDate datePublished) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.quantity = quantity;
		this.genre = genre;
		this.datePublished = datePublished;
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
	
	public long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public LocalDate getPublishedDate() {
		return datePublished;
	}
	public void setPublishedDate(LocalDate datePublished) {
		this.datePublished = datePublished;
	}
	
	
}
