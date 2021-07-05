package com.cg.onlinepizza.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.model.Cart;
import com.cg.onlinepizza.model.CartItem;
import com.cg.onlinepizza.model.Customer;

@Repository
public interface ICartRepositoryDao extends JpaRepository<Cart,Integer>  {
	
	public Cart findByCustomer(Customer customer);
	

}