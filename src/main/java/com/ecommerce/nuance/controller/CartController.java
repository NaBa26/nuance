package com.ecommerce.nuance.controller;

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
import com.ecommerce.nuance.exception.UserNotFoundException;
import com.ecommerce.nuance.model.Cart;
import com.ecommerce.nuance.model.User;
import com.ecommerce.nuance.repository.CartRepository;
import com.ecommerce.nuance.service.CartService;
import com.ecommerce.nuance.service.UserService;

@RestController
@RequestMapping("/cart")
public class CartController 
{
	private CartService cartService;
	
	@Autowired
	 public CartController(CartService cartService) {
        this.cartService=cartService;
    }
	
	@GetMapping
	public List<Cart> getAllItems()
	{
		return cartService.getAllItems();
	}
	
	@GetMapping("/{cartId}")
    public Optional<Cart> getCartById(@PathVariable String cartId) {
        return this.cartService.getCartById(Long.parseLong(cartId));
    }

	@PostMapping(consumes = "application/json")
    public Cart createCart(@RequestBody Cart cart) 
	{
		return this.cartService.createCart(cart);
    }

    @PutMapping("/{cartId}")
    public Cart updateCart(@PathVariable long cartId, @RequestBody Cart updatedCart) throws CartNotFoundException 
    {
    	return this.cartService.updateCart(cartId, updatedCart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable String cartId) 
    {
    	try {
    		this.cartService.deleteCart(Long.parseLong(cartId));
    		return new ResponseEntity<>(HttpStatus.OK);
    	} catch (Exception e)
    	{
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
