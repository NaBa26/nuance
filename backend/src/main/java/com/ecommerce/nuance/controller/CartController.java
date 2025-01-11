package com.ecommerce.nuance.controller;
import com.ecommerce.nuance.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.nuance.model.Cart;

@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@RequestParam("id") long userId) {
    	System.out.println(userId);
        return this.cartService.getCartByUserId(userId);
    }

    @PostMapping("/add/{bookId}")
    public ResponseEntity<Cart> addToCart(@PathVariable long bookId, @RequestParam("id") long userId, @RequestParam("quantity") int quantity) {
        return cartService.addToCart(bookId, userId, quantity);
    }

    @PutMapping("/{bookId}/increase")
    public ResponseEntity<?> increaseQuantity(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.increaseQuantity(bookId, userId);
    }

    @PutMapping("/{bookId}/decrease")
    public ResponseEntity<?> decreaseQuantity(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.decreaseQuantity(bookId, userId);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<?> deleteBookFromCart(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.deleteBookFromCart(bookId, userId);
    }
}
