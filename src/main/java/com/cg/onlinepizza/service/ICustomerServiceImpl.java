package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.Exceptions.CustomerIdNotFoundException;
import com.cg.onlinepizza.dao.ICustomerRepository;
import com.cg.onlinepizza.model.Customer;



@Service
public class ICustomerServiceImpl implements ICustomerService {
	
	
	@Autowired
	ICustomerRepository customerRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		
		Customer cust=customerRepo.save(customer);
		
		return cust;
	}

	@Override
	public Customer updateCustomer(Customer customer)throws CustomerIdNotFoundException{
		boolean b=customerRepo.existsById(customer.getCustomerId());
		if(!b) {
			throw new  CustomerIdNotFoundException("No Customer found for this id");
		}
		else {
			
			
			return customerRepo.save(customer);
		}
	}

	@Override
	public String deleteCustomer(int customerId) throws CustomerIdNotFoundException {
		boolean b=customerRepo.existsById(customerId);
		if(!b) {
			return "No Customer found for this id";
		}
		else {
			customerRepo.deleteById(customerId);
			
			return "Deleted Customer Successfully!";
		}
	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer> customer=customerRepo.findAll();
		return customer;
	}

	@Override
	public Optional<Customer> viewCustomer(int customerId) throws CustomerIdNotFoundException {
		if(!customerRepo.existsById(customerId)) {
			throw new CustomerIdNotFoundException("Customer Not found");
		}else {
		return customerRepo.findById(customerId);
		}
	}

}
