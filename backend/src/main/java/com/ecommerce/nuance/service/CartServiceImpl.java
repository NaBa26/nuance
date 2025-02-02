package com.ecommerce.nuance.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.model.Cart;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.BookRepository;
import com.ecommerce.nuance.repository.CartRepository;
import com.ecommerce.nuance.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<List<Cart>> getCartByUserId(long userId) {
        try {
        	Optional<List<Cart>> cartOptional = cartRepository.findByUser(userId);
            return cartOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
            
        } catch (Exception e) {
        	System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Cart> addToCart(long bookId, long userId, int quantity) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Optional<Cart> cartOptional = cartRepository.findByBookIdAndUserIdAndDeletedAtIsNull(bookId, userId);
            if (cartOptional.isPresent()) {
                System.out.println("Is present -> " + cartOptional);
                Cart cart = cartOptional.get();
                cart.setQuantity(cart.getQuantity() + 1);
                cart.updateSubtotal();
                cart.setDeletedAt(null);
                cart.setUpdatedAt(LocalDateTime.now());
                cartRepository.save(cart);
                return ResponseEntity.ok(cart);
            } else {
                System.out.println("Is not present");
                Cart newCart = new Cart();
                newCart.setUser(userOptional.get());
                newCart.setQuantity(quantity); 
                newCart.setBook(bookRepository.findById(bookId).get());
                newCart.updateSubtotal();
                newCart.setCreatedAt(LocalDateTime.now());
                newCart.setUpdatedAt(LocalDateTime.now());
                cartRepository.save(newCart);
                return ResponseEntity.ok(newCart);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.internalServerError().build();
        }
    }


    @Override
    @Transactional
    public ResponseEntity<Cart> increaseQuantity(long bookId, long userId) {
        try {
            Optional<Cart> cartOptional = cartRepository.findByBookIdAndUserId(bookId, userId);
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Book> bookOptional = bookRepository.findById(bookId);

            if (userOptional.isEmpty() || bookOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return cartOptional.map(cart -> {
                if (cart.getDeletedAt() != null) {
                    cart.setDeletedAt(null);     
                    cart.setQuantity(1);
                    cart.updateSubtotal();
                    cart.setUpdatedAt(LocalDateTime.now());
                    cartRepository.save(cart);
                } else {
                    cart.setQuantity(cart.getQuantity() + 1);
                    cart.updateSubtotal();
                    cart.setUpdatedAt(LocalDateTime.now());
                    cartRepository.save(cart); 
                }
                return ResponseEntity.ok(cart);
            }).orElseGet(() -> {
                Cart newCart = new Cart();
                newCart.setUser(userOptional.get());
                newCart.setQuantity(1);
                newCart.setBook(bookOptional.get());
                newCart.updateSubtotal();
                newCart.setCreatedAt(LocalDateTime.now());
                newCart.setUpdatedAt(LocalDateTime.now());
                cartRepository.save(newCart);
                return ResponseEntity.ok(newCart);
            });
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); 
        }
    }


    @Override
    @Transactional
    public ResponseEntity<Cart> decreaseQuantity(long bookId, long userId) {
        try {
            Optional<Cart> cartOptional = cartRepository.findByBookIdAndUserId(bookId, userId);
            return cartOptional.map(cart -> {
                if (cart.getQuantity() > 0) {
                    cart.setQuantity(cart.getQuantity() - 1);
                    cart.updateSubtotal();
                    cart.setUpdatedAt(LocalDateTime.now());
                    cartRepository.save(cart);
                    return ResponseEntity.ok(cart);
                } else {
                    return ResponseEntity.badRequest().body(cart);
                }
            }).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<Cart> restoreBook(long bookId, long userId) {
        Optional<Cart> cartOptional = cartRepository.findByBookIdAndUserId(bookId, userId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            if (cart.getDeletedAt() != null) {
                cart.setDeletedAt(null);  // Restore the book by setting deletedAt to null
                cart.setQuantity(1);      // Reset quantity to 1
                cart.setUpdatedAt(LocalDateTime.now());
                cartRepository.save(cart);
                return ResponseEntity.ok(cart);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    @Transactional
    public ResponseEntity<String> deleteBookFromCart(long bookId, long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            // Set quantity to zero
            int rowsAffected = cartRepository.setQuantityToZero(bookId, userId);
            System.out.println(bookId + "" + rowsAffected + "" + userId);
            if (rowsAffected > 0) {
                
                int softDeleted = cartRepository.softDeleteBook(bookId, userId);
                
                if (softDeleted > 0) {
                	cartRepository.flush();
                    return ResponseEntity.ok("Book removed from cart successfully.");
                }
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found for the given user and book.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }


}
