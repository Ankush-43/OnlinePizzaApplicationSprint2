package com.cg.onlinepizza.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.model.CartItem;


import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.model.Customer;
@Repository
public interface ICartItemRepositoryDao extends JpaRepository<CartItem, Integer>{
	@Autowired
	public List<CartItem> findByCustomer(Customer customer);

}
