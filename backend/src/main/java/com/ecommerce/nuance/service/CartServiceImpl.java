package com.ecommerce.nuance.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.nuance.model.Cart;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.CartRepository;
import com.ecommerce.nuance.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository; // Inject the UserRepository to fetch User entities

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Cart> getCartByUserId(long userId) {
        try {
            Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
            return cartOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Cart> addToCart(long bookId, long userId, int quantity) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);  // Fetch the User entity
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            User user = userOptional.get();

            Optional<Cart> cartOptional = cartRepository.findByBookIdAndUserIdAndDeletedAtIsNull(bookId, userId);
            if (cartOptional.isPresent()) {
                Cart cart = cartOptional.get();
                if (cart.getDeletedAt() != null) {
                    cart.setQuantity(quantity);
                    cart.updateSubtotal();
                    cart.setDeletedAt(null);
                    cart.setUpdatedAt(LocalDateTime.now());
                    cartRepository.save(cart);
                    return ResponseEntity.ok(cart);
                } else {
                    cart.setQuantity(cart.getQuantity() + quantity);
                    cart.updateSubtotal();
                    cart.setUpdatedAt(LocalDateTime.now());
                    cartRepository.save(cart);
                    return ResponseEntity.ok(cart);
                }
            } else {
                Cart newCart = new Cart();
                newCart.setUser(user);  // Set the whole User entity
                newCart.setQuantity(quantity);
                newCart.updateSubtotal();
                newCart.setCreatedAt(LocalDateTime.now());
                newCart.setUpdatedAt(LocalDateTime.now());
                cartRepository.save(newCart);
                return ResponseEntity.ok(newCart);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Cart> increaseQuantity(long bookId, long userId) {
        try {
            Optional<Cart> cartOptional = cartRepository.findByBookIdAndUserId(bookId, userId);
            return cartOptional.map(cart -> {
                cart.setQuantity(cart.getQuantity() + 1);
                cart.updateSubtotal();
                cart.setUpdatedAt(LocalDateTime.now());
                cartRepository.save(cart);
                return ResponseEntity.ok(cart);
            }).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Cart> decreaseQuantity(long bookId, long userId) {
        try {
            Optional<Cart> cartOptional = cartRepository.findByBookIdAndUserId(bookId, userId);
            return cartOptional.map(cart -> {
                if (cart.getQuantity() > 1) {
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
    public ResponseEntity<String> deleteBookFromCart(long bookId, long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);  // Fetch the User entity
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
            User user = userOptional.get();

            int rowsAffected = cartRepository.setQuantityToZero(bookId, userId);
            if (rowsAffected > 0) {
                Cart cart = cartRepository.findByBookIdAndUserId(bookId, userId).get();
                cart.setDeletedAt(LocalDateTime.now());
                cart.setUpdatedAt(LocalDateTime.now());
                cartRepository.save(cart);
                return ResponseEntity.ok("Book quantity set to zero successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found for the given user and book.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while processing the request.");
        }
    }
}
