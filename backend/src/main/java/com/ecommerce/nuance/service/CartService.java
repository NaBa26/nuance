package com.ecommerce.nuance.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.nuance.model.Cart;

public interface CartService {

	List<Cart> getAllItems();

	Optional<Cart> getCartById(long long1);

	Cart createCart(Cart cart);

	Cart updateCart(long cartId, Cart updatedCart);

	void deleteCart(long long1);

}
