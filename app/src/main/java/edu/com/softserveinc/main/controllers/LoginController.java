package edu.com.softserveinc.main.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.UserService;
import edu.com.softserveinc.main.utils.PasswordEncoder;

@Controller
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody UserModel login(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception { 	  
		
		UserModel user = service.getUserByLogin(username);
		try{
			System.out.println(user.getName() + " : " + user.getPassword());
			System.out.println(username + " : " + password);
		}
		catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
		}
		
		if(new PasswordEncoder(11).compare(password, user.getPassword()))
	    	return user;
	    else 
	    	return null;
	  } 
}
