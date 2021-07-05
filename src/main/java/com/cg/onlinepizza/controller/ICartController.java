package com.cg.onlinepizza.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cg.onlinepizza.Exceptions.CustomerIdNotFoundException;
import com.cg.onlinepizza.Exceptions.ItemIdNotFoundException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.model.Cart;
import com.cg.onlinepizza.model.CartItem;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.service.ICartServiceImpl;

/*
 * Author : ANKUSH KUMAR BOSE
 * Version : 1.0
 * Date : 18-05-2021
 * Description : This is Cart Controller
*/

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class ICartController {
	
	static final Logger LOGGER = LoggerFactory.getLogger(ICartController.class);
	
	@Autowired
	ICartServiceImpl serviceobj;
	
	@GetMapping("/viewAllCartitems/{id}")
	public ResponseEntity<Object> viewAllCartitem(@PathVariable("id") int custid) throws CustomerIdNotFoundException{
		LOGGER.info("View All Cartitems");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<CartItem> p = serviceobj.viewitemList(custid);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		return serviceobj.viewitemList(custid);
	}
	@GetMapping("/viewAllCartitems")
	public List<CartItem> viewAllCartitems(){
		LOGGER.info("View All Cartitems");
		return serviceobj.viewallitemList();
	}
	@GetMapping("/viewCart/{id}")
	public ResponseEntity<Object> viewCart(@PathVariable("id") int custid)  throws CustomerIdNotFoundException{
		LOGGER.info("View All Cartitems");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Optional<Cart> p = serviceobj.viewCart(custid);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		return serviceobj.viewCart(custid);
	}
	@GetMapping("/viewCart")
	public List<Cart> viewAllCarts() {
		LOGGER.info("View All Cartitems");
		return serviceobj.viewCarts();
	}
	/************************************************************************************
	 * Method: addPizzaToCart
	 * Description: It is used to add cartItem into cartItem table
	 * @param cartItem: cartItems reference variable and int id
	 * @returns cartItem: It returns cartItem with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- ANKUSH KUMAR BOSE
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/
	
	@PostMapping("/addItem")
	public ResponseEntity<Object> addPizzaToCart(@RequestBody Map<String, Object> requestData) throws CustomerIdNotFoundException, PizzaIdNotFoundException {
		LOGGER.info("add-item URL is opened");
		LOGGER.info("addPizzaToCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			CartItem p = serviceobj.addPizzaToCart(requestData);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException | PizzaIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		CartItem p = serviceobj.addPizzaToCart(requestData);
//		if (p == null) {
//			return new ResponseEntity("Id Not Found!!", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<CartItem>(p, HttpStatus.OK);
	}
//	@PostMapping("/addItem")
//	public ResponseEntity<CartItem> addPizzaToCart(@RequestBody CartItem cartitem ) throws CustomerIdNotFoundException, PizzaIdNotFoundException {
//		LOGGER.info("add-item URL is opened");
//		LOGGER.info("addPizzaToCart() is initiated");
//		CartItem p = serviceobj.addPizzaToCart(cartitem);
//		if (p == null) {
//			return new ResponseEntity("Id Not Found!!", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<CartItem>(p, HttpStatus.OK);
//	}
	
	/************************************************************************************
	 * Method: createCart
	 * Description: It is used to create a cart into cart table
	 * @param cartItem: int customerId
	 * @returns cart: It returns cart with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- ANKUSH KUMAR BOSE
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/
	
	@PostMapping("/createCart/{id}")
	public ResponseEntity<Object> createCart(@PathVariable("id") int customerId) throws CustomerIdNotFoundException{
		LOGGER.info("create-cart URL is opened");
		LOGGER.info("createCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		Cart p= serviceobj.createCart(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		if (p == null) {
//			return new ResponseEntity("Id Not Found!!", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Cart>(p, HttpStatus.OK);
	}
	
	/************************************************************************************
	 * Method: updateCartQuantity
	 * Description: It is used to update a cartItem using quantity into cartItem table
	 * @param cartItem: int customerId,int itemId,int quant.
	 * @returns cartItem: It returns cartItem with details
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- ANKUSH KUMAR BOSE
     * Created Date -  16-05-2021 
	 * 
	 ************************************************************************************/
	
	@PutMapping("/updateCartQuantity/{customerid}/{itemid}/{quant}")
	public ResponseEntity<Object> updateCartQuantity(@PathVariable("customerid") int customerId,@PathVariable("itemid") int itemId,@PathVariable("quant") int quant) throws CustomerIdNotFoundException, ItemIdNotFoundException{
		LOGGER.info("update-cart-quantity URL is opened");
		LOGGER.info("updateCartQuantity() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
		CartItem p= serviceobj.updateCartQuantity(customerId,itemId,quant);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException | ItemIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		catch(ItemIdNotFoundException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
		
		
	}
	
	/************************************************************************************
	 * Method: deleteCartItem
	 * Description: It is used to delete a cartItem fromm cartItem table
	 * @param cartItem: int customerId,int itemId.
	 * @returns String: It returns a String message
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- ANKUSH KUMAR BOSE
     * Created Date -  16-05-2021 
	 * 
	 ************************************************************************************/
	
	@DeleteMapping("/deleteItemFromCart/{cutomerid}/{itemid}")
	public ResponseEntity<Object> deleteCartItem(@PathVariable("cutomerid") int customerId,@PathVariable("itemid") int itemId) throws CustomerIdNotFoundException, ItemIdNotFoundException{
		LOGGER.info("update-cart-quantity URL is opened");
		LOGGER.info("updateCartQuantity() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p= serviceobj.deleteCartItem(customerId,itemId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException | ItemIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		String p= serviceobj.deleteCartItem(customerId,itemId);
//		if (p == null) {
//			return new ResponseEntity("Id Not Found!!", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
	
	/************************************************************************************
	 * Method: clearCart
	 * Description: It is used to delete a cart from cart table
	 * @param cartItem: int customerId.
	 * @returns String: It returns a String message
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
	 * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- ANKUSH KUMAR BOSE
     * Created Date -  16-05-2021 
	 * 
	 ************************************************************************************/
	
	@DeleteMapping("/clearCart/{id}")
	public ResponseEntity<Object> clearCart(@PathVariable("id") int customerId) throws CustomerIdNotFoundException{
		LOGGER.info("clear-cart URL is opened");
		LOGGER.info("clearCart() is initiated");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			String p= serviceobj.clearCart(customerId);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(CustomerIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		String p= serviceobj.clearCart(customerId);
//		if (p == null) {
//			return new ResponseEntity("Id Not Found!!", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>("No Item in cart", HttpStatus.OK);
	}
	

}
