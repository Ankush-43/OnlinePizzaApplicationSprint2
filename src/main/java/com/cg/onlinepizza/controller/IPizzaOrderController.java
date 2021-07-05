package com.cg.onlinepizza.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlinepizza.Exceptions.CartIdNotFoundException;
import com.cg.onlinepizza.Exceptions.CoupanIdNotFoundException;
import com.cg.onlinepizza.Exceptions.CustomerIdNotFoundException;
import com.cg.onlinepizza.Exceptions.OrderIdNotFoundException;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.service.IPizzaOrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Author       :  PUSHPAN BHAUMIK
 * Version      :  1.0
 * Date         :  18-05-2021
 * Description  :  This is Order Controller 
 */



@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class IPizzaOrderController {
	
	static final Logger LOGGER = LoggerFactory.getLogger(IPizzaOrderController.class);
	
	//Marks a constructor,field, setter method,or configure method as to be Autowired by Spring's dependency injection facilities.
	@Autowired 
	IPizzaOrderServiceImpl service;
	
	
	/************************************************************************************
	 * Method: viewAllOrder
	 * Description: It is used to view all order details from order table
	 * @returns List<Order> It returns List of Orders with details.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-PUSHPAN BHAUMIK
     * Created Date -  18-05-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/viewAllOrder")  
	public List<Order> viewAllOrder(){
	  LOGGER.info("View All Orders");
	  List<Order> orderList= service.listAllOrder();
	  return orderList;
	}	
	
	
	/************************************************************************************
	 * Method: bookOrderInfo
	 * Description   : It is used to add Orders in the Order Table
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * @param        : int cartid,int customerId,int couponId
	 * @returns      : It returns a String 
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * Created By- PUSHPAN BHAUMIK
     * Created Date -  18-05-2021
	 * 
	 ************************************************************************************/

			
	@PostMapping("/insertOrder/{cartid}/{customerId}/{couponId}")
	public ResponseEntity<Object> bookOrderInfo(@PathVariable("cartid")int cartId,@PathVariable("customerId") int customerId,@PathVariable("couponId") int couponId) throws CartIdNotFoundException,CustomerIdNotFoundException,CoupanIdNotFoundException
	{
	  LOGGER.info("bookOrder-info URL is opened");
	  LOGGER.info("bookOrder() is initiated");
	  Map<String,Object> res=new HashMap<String,Object>();
	  try {
		  Order p=service.bookOrderInfo(cartId, customerId, couponId);
		  res.put("status", HttpStatus.OK.value());
		  res.put("data", p);
		  return new ResponseEntity<>(res,HttpStatus.OK);
	  }catch(CartIdNotFoundException | CustomerIdNotFoundException | CoupanIdNotFoundException e)
	  {
	  res.put("status",HttpStatus.NOT_FOUND.value());
	  res.put("data",e.getMessage());
	  return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	  }
	}
	
	/************************************************************************************
	 * Method: cancelOrder
	 * Description: It is used to delete an order fromm order table
	 * @param order: int orderId
	 * @returns String: It returns a String message
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- PUSHPAN BHAUMIK
     * Created Date -  18-05-2021 
	 * 
	 ************************************************************************************/
		
		
	@DeleteMapping("/DeleteOrder/{orderid}")
	public ResponseEntity<String> cancelOrder(@PathVariable("orderid")int id)throws OrderIdNotFoundException
    {
		
		String p= service.cancelOrder(id);
		if (p == null) {
			return new ResponseEntity("Id Not Found!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Order Deleted Successfully", HttpStatus.OK);
    }
	
	
	
	/************************************************************************************
	 * Method: updateOrder
	 * Description: It is used to update an order in the order table
	 * @returns cart: It returns order with updated order details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- PUSHPAN BHAUMIK
     * Created Date -  18-05-2021 
	 * 
	 ************************************************************************************/
	
		
    @PutMapping("/UpdateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order)
    {
      Order ord=service.updateOrder(order);
      if(ord==null){
      return new ResponseEntity("Order Not Found",HttpStatus.NOT_FOUND);
 	}
 	   return new ResponseEntity<Order>(ord ,HttpStatus.OK);
 	}

    
    

	/************************************************************************************
	 * Method: viewAllOrder
	 * Description: It is used to view all order details from order table
	 * @param : int customerId,int orderId
	 * @returns  It returns List of Orders with details.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By-PUSHPAN BHAUMIK
     * Created Date -  18-05-2021 
	 * 
	 ************************************************************************************/
    
    
    
         
    @GetMapping("/viewOrderByOrderandCustomerId/{customerId}")
     public ResponseEntity<Object> viewOrder(@PathVariable("customerId") int customerId) throws CustomerIdNotFoundException
    {
      LOGGER.info("View Your Orders");
      Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<Order> p=service.viewOrder(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//      return service.viewOrder(customerId, orderid);
    }
    }






