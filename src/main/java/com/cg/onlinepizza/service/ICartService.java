package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cg.onlinepizza.Exceptions.CustomerIdNotFoundException;
import com.cg.onlinepizza.Exceptions.ItemIdNotFoundException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.model.Cart;
import com.cg.onlinepizza.model.CartItem;
import com.cg.onlinepizza.model.Pizza;

public interface ICartService {

	public Cart createCart(int customerId)throws CustomerIdNotFoundException;
	public CartItem updateCartQuantity(int customerId, int itemId, int quantity)throws CustomerIdNotFoundException,ItemIdNotFoundException;
	public String deleteCartItem(int customerId, int itemId)throws CustomerIdNotFoundException,ItemIdNotFoundException;
	public String clearCart(int customerId)throws CustomerIdNotFoundException;
	public CartItem addPizzaToCart(Map<String, Object> requestData)throws CustomerIdNotFoundException,PizzaIdNotFoundException;
//	public CartItem addPizzaToCart(CartItem cartitem)throws CustomerIdNotFoundException,PizzaIdNotFoundException;
	public List<CartItem> viewitemList(int custid)throws CustomerIdNotFoundException;
	public List<CartItem> viewallitemList();
	public List<Cart> viewCarts();
	public Optional<Cart> viewCart(int custid)throws CustomerIdNotFoundException;
}
