package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.onlinepizza.Exceptions.InvalidMinCostException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.dao.IPizzaRepositoryDao;
import com.cg.onlinepizza.model.Pizza;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

/***************************************************************************************************************************
 * Class: IPizzaRepositoryImpl 
 * Description: It is used to perform all the business logic in the service layer 
 * Created By-BANHISHIKA CHANDA 
 * Created Date- 15-05-2021
 * 
 ***************************************************************************************************************************/

@Service
@Transactional
public class IPizzaServiceImpl implements IPizzaService {
	@Autowired
	IPizzaRepositoryDao dao;

	/***************************************************************************************************************************
	 * Method: addPizza 
	 * Description: It is used to add one pizza details from customer table 
	 * parameter customer: all pizza details in the body of postman to customer bean and customer bean is sent as a parameter 
	 * Returns Bean: It returns the pizza details stored in the database 
	 * Created By-BANHISHIKA CHANDA
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	@Override
	public Pizza addPizza(Pizza pizza) {
		Pizza p = dao.saveAndFlush(pizza);
		return p;
	}

	/***************************************************************************************************************************
	 * Method: deletePizza 
	 * Description: It is used to delete one pizza details from pizza table if the id sent in the body matches 
	 *              otherwise throw PizzaIdNotFoundException. 
	 * parameter customer: The pizza id to be deleted
	 * Returns : It returns the delete message 
	 * Created By-BANHISHIKA CHANDA Created
	 * Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	@Override
	public String deletePizza(int pizzaId) throws PizzaIdNotFoundException {

		if (!dao.existsById(pizzaId)) {
			throw new PizzaIdNotFoundException("No Pizza found for the user id");
		} else {
			dao.deleteById(pizzaId);
			return "Deleted Pizza";
		}
	}

	/***************************************************************************************************************************
	 * Method: viewPizza 
	 * Description: It is used to view one pizza details from pizza table which matches the pizza id sent in the url 
	 *              otherwise throw PizzaIdNotFoundException. 
	 * parameter pizza: The pizza id to be viewed Returns
	 * Customer: It returns the pizza details stored in the database 
	 * Created By-BANHISHIKA CHANDA 
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	@Override
	public Optional<Pizza> viewPizza(int pizzaId) throws PizzaIdNotFoundException // throws PizzaIdNotFoundException
	{
		if (!dao.existsById(pizzaId)) {
			throw new PizzaIdNotFoundException("No Pizza found for the user id");
		} else {
			Optional<Pizza> p = dao.findById(pizzaId);
			System.out.println("Getting data from db" + p);
			return p;
		}
	}

	/***************************************************************************************************************************
	 * Method: viewPizzaList 
	 * Description: It is used to view all pizza details from pizza table 
	 * Returns List: It returns the list of pizza list details stored in the database 
	 * Created By-BANHISHIKA CHANDA 
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	@Override
	public List<Pizza> viewPizzaList() {
		List<Pizza> p = dao.findAll();
		System.out.println("Getting data from db " + p);
		return p;
	}

	/***************************************************************************************************************************
	 * Method: viewPizzaList 
	 * Description: It is used to filter pizza in the given range. 
	 * parameter : minimum cost of pizza and maximum cost of pizza 
	 * Returns Bean: It returns the filtered pizza list details stored in the database
	 * Created By-BANHISHIKA CHANDA 
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	@Override
	public Optional<List<Pizza>> viewPizzaList(double minCost, double maxCost) throws InvalidMinCostException {
		return dao.viewPizzaList(minCost, maxCost);
	}

	/***************************************************************************************************************************
	 * Method: updatePizza 
	 * Description: It is used to update one pizza details from pizza table if the id sent in the body matches otherwise 
	 * throw PizzaIdNotFoundException. 
	 * parameter customer: all pizza details in the body of postman to pizza bean and pizza bean is sent as a parameter 
	 * Returns Bean: It returns the pizza details stored in the database 
	 * Created By-BANHISHIKA CHANDA 
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	@Override
	public Pizza updatePizza(Pizza pizza) {
		if (!dao.existsById(pizza.getPizzaId())) {
			return null;
		} else {
			dao.save(pizza);
			return dao.save(pizza);
		}
	}

}
