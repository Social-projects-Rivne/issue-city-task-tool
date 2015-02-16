package edu.com.softserveinc.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
		
	
	@RequestMapping("get-users")
	public @ResponseBody List<UserModel> getUsersAction() {
		return service.loadUsersList();
	}
		
	
	@RequestMapping(value = "add-new-user", method = RequestMethod.POST)
	public @ResponseBody String addUserAction(@RequestBody UserModel user) {
		String message = null;
		
		try {
			service.addUser(user);
		}
		catch (Exception ex) {
		}
		
		return message;
	}
	
	
	
	@RequestMapping(value = "edit-user/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editUserAction(@ModelAttribute("user") UserModel user) {
		String message = null;
		
		try {
			service.editUser(user);
		} 
		catch (Exception ex) {
		}
		
		return message;
	}
	
	
	
	@RequestMapping(value = "remove-user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String removeUserAction(@PathVariable("id") int id) {
		String message = null;
		
		try {
			service.deleteUser(id);
		} 
		catch (Exception ex) {
		}
		
		return message;
	}
}
