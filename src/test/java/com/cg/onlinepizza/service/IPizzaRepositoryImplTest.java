package com.cg.onlinepizza.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.dao.IPizzaRepositoryDao;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.service.IPizzaService;
import com.cg.onlinepizza.service.IPizzaServiceImpl;
import com.sun.xml.bind.v2.runtime.output.DOMOutput;
/***************************************************************************************************************************
 * Class: IPizzaRepositoryImplTest
 * Description: It is used to test and perform all the business logic in the service layer 
 * Created By-BANHISHIKA CHANDA 
 * Created Date - 15-05-2021
 * 
 ***************************************************************************************************************************/

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class IPizzaRepositoryImplTest {
	@InjectMocks
	private IPizzaServiceImpl service;
	@Mock
	private IPizzaRepositoryDao dao;
	  
	/***************************************************************************************************************************
	 * Method: viewPizzaListTest
	 * Description: It is used to test view all pizza method
	 * Created By-BANHISHIKA CHANDA 
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/


	@Test
	public void viewPizzaListTest()
	{
		List<Pizza> p = new ArrayList<Pizza>();
		  
	    Pizza pizza = new Pizza();
	    pizza.setPizzaId(1);
	    pizza.setPizzaName("pizzaName");
	    pizza.setPizzaType("pizzaType");
	    pizza.setPizzaDescription("pizzaDescription");
	    pizza.setPizzaCost(500.0);
	    pizza.setPizzaQuantity(5);
	    p.add(pizza);
	   
		  
	    Pizza pizza2 = new Pizza();
	    pizza2.setPizzaId(2);
	    pizza2.setPizzaName("pizzawsdrerName");
	    pizza2.setPizzaType("pizzaTydffpe");
	    pizza2.setPizzaDescription("pizzaDeffddffscription");
	    pizza2.setPizzaCost(500.0);
	    pizza2.setPizzaQuantity(5);
	    p.add(pizza2);

		when(service.viewPizzaList()).thenReturn(p); 
		assertEquals(2,service.viewPizzaList().size());
		 
	}
	
	/***************************************************************************************************************************
	 * Method: getPizzaById 
	 * Description: It is used to test view one pizza method 
	 * Created By-BANHISHIKA CHANDA 
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	
	@Test
	public void getPizzaById() throws PizzaIdNotFoundException 
	{
	    Pizza pizza = new Pizza();
	    pizza.setPizzaId(1);
	    pizza.setPizzaName("pizzaName");
	    pizza.setPizzaType("pizzaType");
	    pizza.setPizzaDescription("pizzaDescription");
	    pizza.setPizzaCost(500.0);
	    pizza.setPizzaQuantity(5);
	    
	    when(dao.existsById(pizza.getPizzaId())).thenReturn(true);
	    Mockito.when(dao.findById(1)).thenReturn(Optional.of(pizza));
	    assertEquals(pizza.getPizzaId(),service.viewPizza(1).get().getPizzaId());
		
	}
	
	/***************************************************************************************************************************
	 * Method: deletePizza 
	 * Description: It is used to test delete one pizza method 
	 * Created By-BANHISHIKA CHANDA Created
	 * Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	
	@Test
	public void deletePizza() throws PizzaIdNotFoundException
	{    when(dao.existsById(1000)).thenReturn(true);
		 Mockito.when(service.deletePizza(1000)).thenReturn("Deleted Pizza");
	        
	}
	
	/***************************************************************************************************************************
	 * Method: addPizza 
	 * Description: It is used to test add one pizza method
	 * Created By-BANHISHIKA CHANDA
	 * Created Date - 15-05-2021
	 * 
	 ***************************************************************************************************************************/

	 @Test
	 public void addPizza()
	 {
		 List<Pizza> p = new ArrayList<Pizza>();
		  
		    Pizza pizza = new Pizza();
		    pizza.setPizzaId(1);
		    pizza.setPizzaName("pizzaName");
		    pizza.setPizzaType("pizzaType");
		    pizza.setPizzaDescription("pizzaDescription");
		    pizza.setPizzaCost(500.0);
		    pizza.setPizzaQuantity(5);
		    p.add(pizza);
		    
		    Pizza pizza2 = new Pizza();
		    pizza2.setPizzaId(2);
		    pizza2.setPizzaName("pizzawsdrerName");
		    pizza2.setPizzaType("pizzaTydffpe");
		    pizza2.setPizzaDescription("pizzaDeffddffscription");
		    pizza2.setPizzaCost(500.0);
		    pizza2.setPizzaQuantity(5);
		    p.add(pizza2);
		    
		    when(service.addPizza(pizza)).thenReturn(pizza);
		    when(service.addPizza(pizza2)).thenReturn(pizza2);
		    assertEquals(2,p.size());
		    
		   
		    
	 }
	 
	 
	 /***************************************************************************************************************************
		 * Method: updatePizza 
		 * Description: It is used to test update one pizza method
		 * Created By-BANHISHIKA CHANDA 
		 * Created Date - 15-05-2021
		 * 
		 ***************************************************************************************************************************/

	 @Test
	 public void updatePizza() throws PizzaIdNotFoundException 
	 {
	  
	    Pizza pizza = new Pizza();
	    pizza.setPizzaId(1);
	    pizza.setPizzaName("pizzaName");
	    pizza.setPizzaType("pizzaType");
	    pizza.setPizzaDescription("pizzaDescription");
	    pizza.setPizzaCost(500.0);
	    pizza.setPizzaQuantity(5);
	  
	    when(dao.existsById(pizza.getPizzaId())).thenReturn(true);
	    
	    pizza.setPizzaId(1);
	    pizza.setPizzaName("pizddzaName");
	    pizza.setPizzaType("pizzaTyppe");
	    pizza.setPizzaDescription("pizzaDescription");
	    pizza.setPizzaCost(500.0);
	    pizza.setPizzaQuantity(5);
	  
	    when(service.updatePizza(pizza)).thenReturn(pizza);
	    assertEquals("pizddzaName", pizza.getPizzaName());
	  
		 
	 }
	

}
