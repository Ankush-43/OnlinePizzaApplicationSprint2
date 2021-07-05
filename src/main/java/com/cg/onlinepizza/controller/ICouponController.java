package com.cg.onlinepizza.controller;

import java.util.List;

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

import com.cg.onlinepizza.Exceptions.CoupanIdNotFoundException;
import com.cg.onlinepizza.Exceptions.PizzaIdNotFoundException;
import com.cg.onlinepizza.model.Coupan;
import com.cg.onlinepizza.model.Pizza;
import com.cg.onlinepizza.service.ICoupanServiceImpl;
/*
 * Author : ABHISHEK SHAW
 * Version : 1.0
 * Date : 18-05-2021
 * Description : This is Coupan Controller
*/
@RestController
@RequestMapping("/coupan")
@CrossOrigin(origins = "http://localhost:4200")
public class ICouponController {
	
	static final Logger LOGGER= LoggerFactory.getLogger(ICouponController.class);
	
	@Autowired
	 ICoupanServiceImpl serviceobj;
	
	/************************************************************************************
	 * Method: addCoupan
	 * Description: It is used to add Coupan into Coupan table
	 * @returns ResponseEntity: It returns Coupan with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- Abhishek Shaw
     * Created Date -  18-05-2021
	 * 
	 ************************************************************************************/
	@PostMapping("/add")
	public ResponseEntity<Coupan> addPizza(@RequestBody Coupan coupan){
		Coupan c=serviceobj.addCoupans(coupan);
		if(c==null){
		return new ResponseEntity("Pizza Not Found!!",HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Coupan Added");
		return new ResponseEntity <Coupan>(c ,HttpStatus.OK);
	}
	
	
	/************************************************************************************
	 * Method: editCoupan
	 * Description: It is used to edit Coupan into Coupan table
	 * return "edited": It returns String "edited"
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- Abhishek Shaw
     * Created Date -  18-05-2021
	 * 
	 ************************************************************************************/
	@PutMapping("/editCoupan")
	public String editCoupan(@RequestBody Coupan coupan)  {
		Coupan c=serviceobj.editCoupans(coupan);
		if(c==null)
			return "not edited";
		else
			LOGGER.info("Coupan Edited");
			return "edited";
		}
	
	
	/************************************************************************************
	 * Method: deleteCoupan
	 * Description: It is used to delete Coupan from Coupan table
	 * @DeleteMapping: It is used to handle the HTTP DELETE requests matched with given URI expression.
     * @PathVariable : It is used to handle template variables in the request URI mapping,and use them as method parameters.
	 * Created By- Abhishek Shaw
     * Created Date -  18-05-2021
	 * 
	 ************************************************************************************/
	@DeleteMapping("/delete/{id}")
	public String deleteCoupan(@PathVariable("id") int id) throws CoupanIdNotFoundException {
		LOGGER.info("Coupan Deleted");
		return serviceobj.deleteCoupans(id);
	}
	
	
	/************************************************************************************
	 * Method: viewAllCoupan
	 * Description: It is used to add Coupan into Coupan table
	 * @returns serviceobj : It returns Coupan with details
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By- Abhishek Shaw
     * Created Date -  18-05-2021
	 * 
	 ************************************************************************************/
	@GetMapping("/viewAllCoupan")
	public List<Coupan> viewAllCoupan() {
		return serviceobj.viewCoupans(); 
	}
}
