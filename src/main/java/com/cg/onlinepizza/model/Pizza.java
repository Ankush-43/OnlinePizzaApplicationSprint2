package com.cg.onlinepizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/***************************************************************************************************************************
 * Class: Pizza
 * Description: It is the pizza entity
 * Created By-BANHISHIKA CHANDA
 * Created Date -  15-05-2021 
 * 
 ***************************************************************************************************************************/

@Entity
@Table(name = "pizza")
public class Pizza {
	public Pizza() {
		
	}
	@Id
	@GeneratedValue
	private int pizzaId;
	private String pizzaType;
	private String pizzaName;
	private String pizzaDescription;
	private double pizzaCost;
	
	public Pizza(int pizzaId, String pizzaType, String pizzaName, String pizzaDescription, double pizzaCost, int pizzaQuantity) {
		this.pizzaId = pizzaId;
		this.pizzaType = pizzaType;
		this.pizzaName = pizzaName;
		this.pizzaDescription = pizzaDescription;
		this.pizzaCost = pizzaCost;
		this.pizzaQuantity = pizzaQuantity;
	}
	public int getPizzaQuantity() {
		return pizzaQuantity;
	}
	public void setPizzaQuantity(int pizzaQuantity) {
		this.pizzaQuantity = pizzaQuantity;
	}
	private int pizzaQuantity;
	public int getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
	public String getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getPizzaDescription() {
		return pizzaDescription;
	}
	public void setPizzaDescription(String pizzaDescription) {
		this.pizzaDescription = pizzaDescription;
	}
	public double getPizzaCost() {
		return pizzaCost;
	}
	public void setPizzaCost(double pizzaCost) {
		this.pizzaCost = pizzaCost;
	}
	@Override
	public String toString()
	{
		return pizzaId+" "+pizzaName+" "+pizzaType+" "+pizzaDescription+" "+pizzaCost+" "+pizzaQuantity;
		
	}
	
	
}
