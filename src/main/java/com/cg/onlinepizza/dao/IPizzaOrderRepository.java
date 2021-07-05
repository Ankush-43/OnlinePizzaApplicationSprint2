package com.cg.onlinepizza.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.model.Cart;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Order;

@Repository
public interface IPizzaOrderRepository extends JpaRepository<Order,Integer>
{

	public List<Order>  findByCustomer(Optional<Customer> byId);
	
	public List<Cart>  findByCart(Cart cart);
	
	public String deleteById(int orderId);
}