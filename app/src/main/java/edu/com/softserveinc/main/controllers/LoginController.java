package edu.com.softserveinc.main.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.dao.UserDao;
import edu.com.softserveinc.main.models.UserModel;

@Controller
public class LoginController {
	
	  @RequestMapping(value = "/login", method = RequestMethod.POST)
	  public @ResponseBody String login(@RequestParam("username") String username, @RequestParam("password") String password) { 	  
		
		UserDao userDao = null;
	   // @SuppressWarnings({ "null", "unused" })
		//UserModel details = (UserModel)userDao.findByName(username);
		try{
			System.out.println((userDao.findByName("11gsdfg")));
		}
		catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
		}
	    return "Success";
	    
	  } 
}
