package edu.com.softserveinc.main.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception { 	  
		UserModel user = service.getUserByName(username);
		try{
			
			System.out.println(user.getPassword());
			System.out.println(username);
			
		}
		catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
		}
	    return "Success";
	    
	  } 
}
