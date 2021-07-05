package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Optional;

import com.cg.onlinepizza.Exceptions.InvalidMinCostException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.model.Pizza;

/***************************************************************************************************************************
 * Interface: IPizzaRepository
 * Description: It specify all the business logic in the service layer
 * Created By-BANHISHIKA CHANDA
 * Created Date -  15-05-2021 
 * 
 ***************************************************************************************************************************/

public interface IPizzaService{
	Pizza addPizza(Pizza pizza);

	Pizza updatePizza(Pizza pizza) throws PizzaIdNotFoundException;

	String deletePizza(int pizzaId) throws PizzaIdNotFoundException; //throws PizzaIdNotFoundException;

	Optional<Pizza> viewPizza(int pizzaId) throws PizzaIdNotFoundException; //throws PizzaIdNotFoundException;

	List<Pizza> viewPizzaList();

	Optional<List<Pizza>> viewPizzaList(double minCost, double maxCost) throws InvalidMinCostException;//throws InvalidMinCostException;
}
