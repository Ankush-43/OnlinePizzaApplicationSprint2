package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.Exceptions.CartIdNotFoundException;
import com.cg.onlinepizza.Exceptions.CoupanIdNotFoundException;
import com.cg.onlinepizza.Exceptions.CustomerIdNotFoundException;
import com.cg.onlinepizza.Exceptions.OrderAlreadyBookedException;
import com.cg.onlinepizza.Exceptions.OrderIdNotFoundException;
import com.cg.onlinepizza.dao.ICustomerRepository;
import com.cg.onlinepizza.dao.IPizzaOrderRepository;
import com.cg.onlinepizza.dao.ICartRepositoryDao;
import com.cg.onlinepizza.dao.ICouponRepositoryDao;
import com.cg.onlinepizza.model.Order;
import com.cg.onlinepizza.model.Cart;
import com.cg.onlinepizza.model.Coupan;
import com.cg.onlinepizza.model.Customer;

@Service
@Transactional
public class IPizzaOrderServiceImpl implements IPizzaOrderService {

	@Autowired
	IPizzaOrderRepository repo;

	@Autowired
	ICustomerRepository custdao;

	@Autowired
	ICartRepositoryDao cartrepo;
	
	@Autowired
	ICouponRepositoryDao coupondao;

	
	
	// Customer can check the Order Status by giving his customerId and orderId
	public List<Order> viewOrder(int customerId) throws CustomerIdNotFoundException {
		Optional<Customer> byId = custdao.findById(customerId);
		
		if (!byId.isPresent()) {
			throw new CustomerIdNotFoundException("Wrong Customer Id");
		} else {
				List<Order> ord = repo.findByCustomer(byId);
				double finalprice=0.0;
				for(int i=0;i<ord.size();i++) {
				double finalPrice = (ord.get(i).getCart().getTotal()) - (ord.get(i).getCoupan().getCoupanDiscount());
				System.out.println(finalPrice);
				ord.get(i).setFinalPrice(finalPrice);
				repo.save(ord.get(i));
				}
				return repo.findByCustomer(byId);
			
		}
	}

	
	
	// Returns a list of all the orders
	@Override
	public List<Order> listAllOrder() {
		System.out.println("List all orders in service layers");
		List<Order> list = repo.findAll();
		return list;
	}

	// Customer can Update His Order
	@Override
	public Order updateOrder(Order ord) throws OrderIdNotFoundException {
		boolean b = repo.existsById(ord.getOrderId());
		if (!b) {
			throw new OrderIdNotFoundException();
		} else
			System.out.println("Update Order Successfully");
		return repo.save(ord);
	}

	
	
	
	
	// Customer can Delete The Particular Order
	public String cancelOrder(int orderid) throws OrderIdNotFoundException {
		if (!repo.existsById(orderid))
			throw new OrderIdNotFoundException();
		else
			repo.deleteById(orderid);

		return "Order Deleted Successfully";
	}

	
	
	
	// Customer can book his/her orders here by mentioning his Customer and Cart Id
//	public Order bookOrderInfo(int cartId, int customerId,int coupanId) throws CartIdNotFoundException{
//		Optional<Customer> byId = custdao.findById(customerId);
//		
//		Optional<Cart> cartIde = cartrepo.findById(cartId);
//		Optional<Coupan> coupanIde=coupondao.findById(coupanId);
//		
//		Order ord = new Order();
//		if (!cartIde.isPresent()) {
//			throw new CartIdNotFoundException("Cart not found");
//		} else {
//			ord.setOrderStatus("Booked");
//			ord.setCustomer(byId.get());
//			ord.setCart(cartIde.get());
//			ord.setCoupan(coupanIde.get());
//			repo.save(ord);
//			return ord;
//		}
//
//	}
	public Order bookOrderInfo(int cartId, int customerId,int coupanId) throws CartIdNotFoundException,CustomerIdNotFoundException, CoupanIdNotFoundException{
		Optional<Customer> byId = custdao.findById(customerId);

		Optional<Cart> cartIde = cartrepo.findById(cartId);
		Optional<Coupan> coupanIde=coupondao.findById(coupanId);

		Order ord = new Order();
		if (!cartIde.isPresent()) {
     		throw new CartIdNotFoundException("Cart not found");
		} 
		else if(!byId.isPresent()) {
			throw new CustomerIdNotFoundException("Customer Id Not Found");
		}
		else if(!coupanIde.isPresent())
		{
			throw new CoupanIdNotFoundException("Coupan Id Not Found");
		}
		else {
			ord.setOrderStatus("Booked");
			ord.setCustomer(byId.get());
			ord.setCart(cartIde.get());
			ord.setCoupan(coupanIde.get());
			repo.save(ord);
			return ord;
		}

	}




}
