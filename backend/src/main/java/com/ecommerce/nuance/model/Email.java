package com.ecommerce.nuance.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="emails")
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String recipient;
	private String subject;
	private String body;
	private String category;
	private LocalDate sent;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getSent() {
		return sent;
	}

	public void setSent(LocalDate sent) {
		this.sent = sent;
	}

	public Email() {}

	public Email(int id, String recipient, String subject, String body, String category, LocalDate sent) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
		this.category = category;
		this.sent = sent;
	}
	
}
