package com.cg.onlinepizza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cartitem")
public class CartItem {
	@Id
	@GeneratedValue
	private int itemId;
	@OneToOne
	@JoinColumn(name = "pizzaId", nullable=false)
	private Pizza pizza;
	
//	@ManyToOne
//	@JoinColumn(name="cart_id")
//	private Cart cart;
//	
//	public Cart getCart() {
//		return cart;
//	}
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}
	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", pizza=" + pizza + ", pizzaSize=" + pizzaSize + ", quantity=" + quantity
				+ ", customer=" + customer + "]";
	}
	private String pizzaSize;
	@Column(nullable=true)
	private int quantity;
	@ManyToOne
	@JoinColumn(name = "customerId", nullable=false)
	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public String getPizzaSize() {
		return pizzaSize;
	}
	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
	

