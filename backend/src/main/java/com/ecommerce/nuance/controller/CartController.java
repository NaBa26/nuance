package com.ecommerce.nuance.controller;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.nuance.exception.CartNotFoundException;
import com.ecommerce.nuance.model.Cart;
import com.ecommerce.nuance.service.CartService;

@RestController
@RequestMapping("api/cart")
public class CartController 
{
	private final CartService cartService;
	
	 public CartController(CartService cartService) {
        this.cartService=cartService;
    }
	
	@GetMapping("/{userId}")
	public List<Cart> getAllItems()
	{
		return cartService.getAllCarts();
	}
	
	@GetMapping("/{cartId}")
    public Optional<Cart> getCartById(@PathVariable String cartId) {
        return this.cartService.getCartById(Long.parseLong(cartId));
    }

	@PostMapping
    public Cart createCart(@RequestBody Cart cart) 
	{
		return this.cartService.createCart(cart);
    }

    @PutMapping("/{cartId}")
    public Cart updateCart(@PathVariable long cartId, @RequestBody Cart updatedCart) throws CartNotFoundException 
    {
    	return this.cartService.updateCart(cartId, updatedCart);
    }
}
