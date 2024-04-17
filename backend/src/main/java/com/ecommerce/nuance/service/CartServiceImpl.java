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
public class CartServiceImpl {

	private final CartRepository cartRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository) // This constructor is created to automatically update the value in the repository class
	{
		this.cartRepository = cartRepository;
	}
	
	public List<Cart> getAllCarts()
	{
		return cartRepository.findAll();
	}
	
	
	public Optional<Cart> getCartById(Long cartId) //can't use long, use Long as ID is of generic type
	{
		return cartRepository.findById(cartId);
	}
	
	
	public Cart createCart(Cart cart)
	{
		if(cart!=null)
		{
		return cartRepository.save(cart);
		}
		return null;
	}
	

    public Cart updateCart(long cartId,Cart updatedCart) throws CartNotFoundException 
    {
        
        return cartRepository.findById(cartId).map(cart->{ 
        	cart.setBookId(updatedCart.getBookId());
        	cart.setCartId(updatedCart.getCartId());
        	cart.setOrderId(updatedCart.getOrderId());
        	cart.setQuantity(updatedCart.getQuantity());
        	cart.setSubtotal(updatedCart.getSubtotal());
        	cart.setUserId(updatedCart.getUserId());
            return cartRepository.save(cart);
        }).orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
    }

    @Transactional
    public void deleteCart(long cartId) 
    {
		cartRepository.deleteById(cartId);
    }
    
    void deleteAll()
    {
    	try {
    	cartRepository.deleteAll();
    	}
    	catch(Exception e)
    	{
    		System.out.print("There are no items present in the cart that can be cleared. Exception: "+ e );
    	}
    }
}
