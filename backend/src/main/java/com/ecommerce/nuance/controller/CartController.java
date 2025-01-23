package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.service.AuthenticationService;
import com.ecommerce.nuance.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    AuthenticationService authenticationService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
   @GetMapping()
   public ResponseEntity<Cart> getCart(@RequestParam("id") long userId) {
        return this.cartService.getCartByUserId(userId);
    }

    @PostMapping("/add/{bookId}")
    public ResponseEntity<Cart> addToCart(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.addToCart(bookId, userId, 1);
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
