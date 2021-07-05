package com.cg.onlinepizza.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.model.Admin;
import com.cg.onlinepizza.service.AdminLogin;

/*
 * Author : ANURON MULLIK
 * Version : 1.0
 * Date : 18/05/2021
 * Description : This is Admin_Login_Controller
*/

@RestController
@RequestMapping("/Admin_Login")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminLoginController {

	@Autowired
	HttpSession session;
	
	@Autowired
	AdminLogin adminLog;
	
	
	/*
	 * Method : register
	 * Description : adding to the database
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created by : ANURON MULLIK
	 * Date of Creation : 17/05/2021
	 */
	
	@PostMapping("/Register")
	public Admin register(@RequestBody Admin admin) {
	
		return adminLog.addNewUser(admin);
	}
	
	/*
	 * Method : login
	 * Description : checking username and password is same or not
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestParam : It is used to read the HTML form data provided by a user and bind it to the request parameter.
	 * Created by : ANURON MULLIK
	 * Date of Creation : 17/05/2021
	 */
	
	@PostMapping("/Login/{admin}/{password}")
    public Admin login(@PathVariable("admin") String adminName,@PathVariable("password") String password)  {
        System.out.println(adminName+","+password);
        return adminLog.signIn(adminName, password);
    }
	
	/*
	 * Method : logout
	 * Description : logging out session
	 * @GetMapping : It is used to handle GET type of request method.
	 * ResponseEntity : It represents the whole HTTP response- status code, headers, and body.
	 * Created by : ANURON MULLIK
	 * Date of Creation : 17/05/2021
	 */
	
	@GetMapping("/Logout")
	public ResponseEntity<String> logout() {
		session.invalidate();
		return new ResponseEntity<String>("logged out",HttpStatus.OK);
	}
	
	}
	

