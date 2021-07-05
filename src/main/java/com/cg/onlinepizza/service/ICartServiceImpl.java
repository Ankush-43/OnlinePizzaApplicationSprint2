package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.Exceptions.CustomerIdNotFoundException;
import com.cg.onlinepizza.Exceptions.ItemIdNotFoundException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.dao.ICartItemRepositoryDao;
import com.cg.onlinepizza.dao.ICartRepositoryDao;
import com.cg.onlinepizza.dao.ICustomerRepository;
import com.cg.onlinepizza.dao.IPizzaRepositoryDao;
import com.cg.onlinepizza.model.Cart;
import com.cg.onlinepizza.model.CartItem;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Pizza;

@Service
public class ICartServiceImpl implements ICartService {
	
	@Autowired
	ICartRepositoryDao cartdao;
	@Autowired
	ICartItemRepositoryDao itemdao;
	@Autowired
	IPizzaRepositoryDao pizzadao;
	@Autowired
	ICustomerRepository customerdao;

	//customer can create a cart by passing customerID
	@Override
	public Cart createCart(int customerId) throws CustomerIdNotFoundException{
		Cart cart=new Cart();
		if (!customerdao.existsById(customerId)) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		} else {
			Customer customer=customerdao.findById(customerId).get();
			cart.setCustomer(customer);
			
			List<CartItem> cartItem = itemdao.findByCustomer(customer);
			double total=0.0;
			for(int i=0; i<cartItem.size(); i++)
			{
				total=total+(cartItem.get(i).getPizza().getPizzaCost())*(cartItem.get(i).getQuantity());
			}
			 cart.setTotal(total);
			cart.setCartItem(cartItem);
			return cartdao.save(cart);
		}
		
		
	}
	@Override
	public List<CartItem> viewitemList(int custid)throws CustomerIdNotFoundException {
		if (!customerdao.existsById(custid)) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		}else {
			Customer customer=customerdao.findById(custid).get();
			List<CartItem> cartItem = itemdao.findByCustomer(customer);
			System.out.println("Getting data from db" + cartItem);
			return cartItem;
		}
	}
	@Override
	public List<CartItem> viewallitemList(){
		List<CartItem> cartitem=itemdao.findAll();
		return cartitem;
	}
	@Override
	public Optional<Cart> viewCart(int custid)throws CustomerIdNotFoundException {
		if (!customerdao.existsById(custid)) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		}else {
			Customer customer=customerdao.findById(custid).get();
			Optional<Cart> cart = Optional.ofNullable(cartdao.findByCustomer(customer));
			System.out.println("Getting data from db" + cart);
			return cart;
		}
	}
	@Override
	public List<Cart> viewCarts(){
		List<Cart> cart=cartdao.findAll();
		return cart;
	}
	//customer can clear the cart created by passing customerId
	@Override
	public String clearCart(int customerId)throws CustomerIdNotFoundException {
		if (!customerdao.existsById(customerId)) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		}else {
			Optional<Customer> byId = customerdao.findById(customerId);
			int cartId=cartdao.findByCustomer(byId.get()).getCartId();
			cartdao.deleteById(cartId);
			return "Cart Deleted";
		}
	}


	//customer can delete cartItem by sending customerId and itemId
	@Override
	public String deleteCartItem(int customerId, int itemId)throws CustomerIdNotFoundException,ItemIdNotFoundException {
		if (!customerdao.existsById(customerId)) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		} else {
			boolean b = itemdao.existsById(itemId);
			if (!b)
				throw new  ItemIdNotFoundException("No CartItem found for this id");
			else {
				itemdao.deleteById(itemId);
				return "deleted";
			}
		}
	}

	//customer can update cart quantity by passing customerId, itemId and quantity.
	@Override
	public CartItem updateCartQuantity(int customerId, int itemId, int quantity)throws CustomerIdNotFoundException,ItemIdNotFoundException {
		if (!customerdao.existsById(customerId)) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		} else {
			Optional<CartItem> byId2 = itemdao.findById(itemId);
			if (!byId2.isPresent())
				throw new  ItemIdNotFoundException("No CartItem found for this id");
			else {
				
				CartItem cartItem = byId2.get();
				cartItem.setQuantity(quantity);
				return itemdao.save(cartItem);
			}

		}
	}

	//customer can add pizza to cartItem by passing customerId and body 
	@Override
	public CartItem addPizzaToCart( Map<String, Object> requestData)throws CustomerIdNotFoundException,PizzaIdNotFoundException {
		CartItem cartItem = new CartItem();
		int customerId=(int) requestData.get("customerId");
		if (!customerdao.existsById(customerId)) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		} else {
			int pizzaId = (int) requestData.get("pizzaId");
			if(!pizzadao.existsById(pizzaId)) {
				throw new  PizzaIdNotFoundException("No Pizza found for this id");
			}

			else {
				Customer customer=customerdao.findById(customerId).get();
				Pizza pizza=pizzadao.findById(pizzaId).get();
				int quantity=(int) requestData.get("quantity");
				String pizzaSize=(String) requestData.get("pizzaSize");
				cartItem.setCustomer(customer);
				cartItem.setPizza(pizza);
				cartItem.setPizzaSize(pizzaSize);
				cartItem.setQuantity(quantity);
				System.out.println(cartItem.toString());
				System.out.println(customer.toString());
				System.out.println(pizza.toString());
				return itemdao.save(cartItem);
			}
			
		}

	}
	
//	@Override
//	public CartItem addPizzaToCart( CartItem cartitem)throws CustomerIdNotFoundException,PizzaIdNotFoundException {
//		CartItem cartItem1 = new CartItem();
//		int customerId=cartitem.getCustomer().getCustomerId();
//		if (!customerdao.existsById(customerId)) {
//			throw new  CustomerIdNotFoundException("No Customer found for this id");
//		} else {
//			int pizzaId = cartitem.getPizza().getPizzaId();
//			if(!pizzadao.existsById(pizzaId)) {
//				throw new  PizzaIdNotFoundException("No Pizza found for this id");
//			}
//
//			else {
//				Customer customer=customerdao.findById(customerId).get();
//				Pizza pizza=pizzadao.findById(pizzaId).get();
//				int quantity=cartitem.getQuantity();
//				String pizzaSize=cartitem.getPizzaSize();
//				cartItem1.setCustomer(customer);
//				cartItem1.setPizza(pizza);
//				cartItem1.setPizzaSize(pizzaSize);
//				cartItem1.setQuantity(quantity);
////				System.out.println(cartItem1.toString());
////				System.out.println(customer.toString());
////				System.out.println(pizza.toString());
//				return itemdao.save(cartItem1);
//			}
//			
//		}
//
//	}
}
