package edu.com.softserveinc.main.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam("username") String username, @RequestParam("password") String password) { 	  
		
		try{
			System.out.println((service.getUserByName("11gsdfg")));
		}
		catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
		}
	    return "Success";
	    
	  } 
}
