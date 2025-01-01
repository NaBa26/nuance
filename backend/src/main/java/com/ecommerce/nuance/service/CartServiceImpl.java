package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.nuance.exception.CartNotFoundException;
import com.ecommerce.nuance.model.Cart;
import com.ecommerce.nuance.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> getCartById(long cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Cart createCart(Cart cart) {
        if (cart != null) {
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public Cart updateCart(long cartId, Cart updatedCart) throws CartNotFoundException {
        return cartRepository.findById(cartId).map(cart -> {
            cart.setBookId(updatedCart.getBookId());
            cart.setCartId(updatedCart.getCartId());
            cart.setOrderId(updatedCart.getOrderId());
            cart.setQuantity(updatedCart.getQuantity());
            cart.setSubtotal(updatedCart.getSubtotal());
            cart.setUserId(updatedCart.getUserId());
            return cartRepository.save(cart);
        }).orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
    }

}

