package com.ecommerce.nuance.controller;

import com.ecommerce.nuance.service.AuthenticationService;
import com.ecommerce.nuance.service.CartService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.ecommerce.nuance.repository.CartRepository;

@RestController
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    
    @Autowired
    AuthenticationService authenticationService;

    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }
    
   @GetMapping()
   public ResponseEntity<List<Cart>> getCart(@RequestParam("id") long userId) {
        return this.cartService.getCartByUserId(userId);
    }
   
   @GetMapping("/{bookId}")
   public ResponseEntity<Cart> getCart(@PathVariable long bookId, @RequestParam("id") long userId) {
       Optional<Cart> cartOptional = this.cartRepository.findByBookIdAndUserId(bookId, userId);
       
       if (cartOptional.isPresent()) {
           return ResponseEntity.ok(cartOptional.get());
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
       }
   }


    @PostMapping("/add/{bookId}")
    public ResponseEntity<Cart> addToCart(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.addToCart(bookId, userId, 1);
    }

    @PutMapping("/increase/{bookId}")
    public ResponseEntity<?> increaseQuantity(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.increaseQuantity(bookId, userId);
    }
    
    @PutMapping("/restore/{bookId}")
    public ResponseEntity<?> restoreBook(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.restoreBook(bookId, userId);
    }

    @PutMapping("/decrease/{bookId}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.decreaseQuantity(bookId, userId);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<?> deleteBookFromCart(@PathVariable long bookId, @RequestParam("id") long userId) {
        return cartService.deleteBookFromCart(bookId, userId);
    }
}
