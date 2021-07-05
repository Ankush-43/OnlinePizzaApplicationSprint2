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
import com.cg.onlinepizza.Exceptions.InvalidMinCostException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.model.Customer;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.service.IPizzaServiceImpl;

/*
 * Author : BANHISHIKA CHANDA
 * Version : 1.0
 * Date : 18-05-2021
 * Description : This is Pizza Controller
*/


@RestController
@RequestMapping("/pizza")
@CrossOrigin(origins = "http://localhost:4200")
public class IPizzaController {
	@Autowired
	private IPizzaServiceImpl serviceobj;
	static final Logger LOGGER = LoggerFactory.getLogger(IPizzaController.class);
	/************************************************************************************
	 * Method: addPizza 
	 * Description: It is used to add pizza into pizza table
	 * @param pizza: pizza's reference variable.
	 * @returns Pizza It returns Pizza with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-BANHISIKHA CHANDA
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/

	@PostMapping("/add")
	public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza) {
		LOGGER.info("Add Pizza");
		Pizza p = serviceobj.addPizza(pizza);
		if (p == null) {
			return new ResponseEntity("Pizza Not Found!!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pizza>(p, HttpStatus.OK);
	}
	
	/************************************************************************************
	 * Method: deletePizza 
	 * Description: It is used to delete pizza into pizza table
	 * @param pizza: int id
	 * @returns String It returns a message that the pizza information is deleted.
	 * @DeleteMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-BANHISIKHA CHANDA
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/

	@DeleteMapping("/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) throws PizzaIdNotFoundException {
		LOGGER.info("Delete Pizza");
		return serviceobj.deletePizza(id); 
	} 
	
	/************************************************************************************
	 * Method: viewAllPizza 
	 * Description: It is used to view all pizza details from pizza table
	 * @returns List<Pizza> It returns List of Pizzas with details.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-BANHISIKHA CHANDA
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/

	@GetMapping("/viewAllPizza")
	public List<Pizza> viewAllPizza() {
		LOGGER.info("View All Pizza");
		return serviceobj.viewPizzaList();
	}

	/************************************************************************************
	 * Method: getPizza
	 * Description: It is used to view one pizza details from pizza table
	 * @param pizza: int id
	 * @returns Optional<Pizza> It returns one Pizza with details.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-BANHISIKHA CHANDA
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/
	
	@GetMapping("/viewpizza/{id}")
	public ResponseEntity<Object> getPizza(@PathVariable("id") int id) throws PizzaIdNotFoundException {
		LOGGER.info("View Pizza Id");
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			Optional<Pizza> p=serviceobj.viewPizza(id);
		res.put("status", HttpStatus.OK.value());
		res.put("data", p);
		return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(PizzaIdNotFoundException e) {
			res.put("status", HttpStatus.NOT_FOUND.value());
			res.put("data", e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		}
//		return serviceobj.viewPizza(id);
	}

	/************************************************************************************
	 * Method: UppdatePizza
	 * Description: It is used to update one pizza details from pizza table
	 * @param pizza: pizza's reference variable.
	 * @returns String It returns a message whether the pizza is updated or not.
	 * @PutMapping: It is used to handle the HTTP PUT requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-BANHISIKHA CHANDA
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/
	
	@PutMapping("/UpdatePizza")
	public ResponseEntity<String> UpdatePizza(@RequestBody Pizza pizza) {
		LOGGER.info("Update Pizza");
		Pizza p= serviceobj.updatePizza(pizza);
		if(p== null)
		return new ResponseEntity("Not Update!!", HttpStatus.NOT_FOUND);
		else
		return new ResponseEntity<>("Updated", HttpStatus.OK);

	}
	
	/************************************************************************************
	 * Method: viewPizzaList
	 * Description: It is used to view list of pizzas inside a range from pizza table
	 * @param pizza: double minCost,double maxCost
	 * @returns Optional<List<Pizza>> It returns List of Pizzas in the range.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By-BANHISIKHA CHANDA
     * Created Date -  15-05-2021 
	 * 
	 ************************************************************************************/

	@GetMapping("/viewPizzaList/{minCost}/{maxCost}")
	public Optional<List<Pizza>> viewPizzaList(@PathVariable("minCost") double minCost,
			@PathVariable("maxCost") double maxCost) throws InvalidMinCostException {
		LOGGER.info("View Pizza In the given range of minimum cost and maximum cost");
		return serviceobj.viewPizzaList(minCost, maxCost);

	}


}
