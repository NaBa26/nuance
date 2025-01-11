package com.ecommerce.nuance.service;


import org.springframework.http.ResponseEntity;
import com.ecommerce.nuance.model.Cart;

public interface CartService {
	
	public ResponseEntity<Cart> getCartByUserId(long userId);
	
	public ResponseEntity<Cart> addToCart(long bookId, long userId, int quantity);

    public ResponseEntity<?> increaseQuantity(long bookId, long userId);
    
    public ResponseEntity<?> decreaseQuantity(long bookId, long userId);
    
    public ResponseEntity<?> deleteBookFromCart(long bookId, long userId);
}
