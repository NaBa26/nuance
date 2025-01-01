package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.nuance.exception.CartNotFoundException;
import com.ecommerce.nuance.model.Cart;

public interface CartService {

    // Retrieve all cart items
    List<Cart> getAllCarts();

    // Retrieve a cart by its ID
    Optional<Cart> getCartById(long cartId);

    // Create a new cart
    Cart createCart(Cart cart);

    // Update an existing cart
    Cart updateCart(long cartId, Cart updatedCart) throws CartNotFoundException;
}
