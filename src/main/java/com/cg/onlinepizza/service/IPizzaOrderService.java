package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Optional;

import com.cg.onlinepizza.Exceptions.CartIdNotFoundException;
import com.cg.onlinepizza.Exceptions.CoupanIdNotFoundException;
import com.cg.onlinepizza.Exceptions.CustomerIdNotFoundException;
import com.cg.onlinepizza.Exceptions.OrderIdNotFoundException;
import com.cg.onlinepizza.model.Order;

public interface IPizzaOrderService {
	
    
    Order bookOrderInfo(int cartId,int customerId,int coupanId )throws CartIdNotFoundException,CustomerIdNotFoundException,CoupanIdNotFoundException;
	
	Order updateOrder(Order ord); //done

	String cancelOrder(int orderId) throws OrderIdNotFoundException; //done

    Iterable<Order> listAllOrder();//done

    List<Order> viewOrder(int customerId) throws CustomerIdNotFoundException; //done

	
    
    
    
}


