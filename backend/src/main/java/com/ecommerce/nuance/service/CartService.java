package com.ecommerce.nuance.service;


import java.util.List;

import org.springframework.http.ResponseEntity;
import com.ecommerce.nuance.model.Cart;

public interface CartService {
	
	public ResponseEntity<List<Cart>> getCartByUserId(long userId);
	
	public ResponseEntity<Cart> addToCart(long bookId, long userId, int quantity);

    public ResponseEntity<?> increaseQuantity(long bookId, long userId);
    
    public ResponseEntity<?> decreaseQuantity(long bookId, long userId);
    
    public ResponseEntity<?> deleteBookFromCart(long bookId, long userId);

	ResponseEntity<Cart> restoreBook(long bookId, long userId);
}
