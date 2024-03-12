package com.ecommerce.nuance.exception;

public class BookNotFoundException extends Exception {

	private static final long serialVersionUID = 1L; // Because Exception is a Serializable class, it's recommended to include the versioning

    public BookNotFoundException(String message) {
        super(message);
    }
	
}
