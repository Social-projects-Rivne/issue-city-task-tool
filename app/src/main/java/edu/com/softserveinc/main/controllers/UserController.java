package edu.com.softserveinc.main.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		
	
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addUserAction(@RequestBody UserModel user,
			Map<String, String> message) {
		
		try {
			service.addUser(user);
			message.put("message", "User was successfully added");
		}
		catch (Exception ex) {
			message.put("message", "Some problem occured! User was not added");
		}
		
		return message;
	}
	
	
	
	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, String> editUserAction(@RequestBody UserModel user,
			Map<String, String> message) {
		
		try {
			service.editUser(user);
			message.put("message", "User was successfully edited");
		} 
		catch (Exception ex) {
			message.put("message", "Some problem occured! User was not edited");
		}
		
		return message;
	}
	
	
	
	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, String> removeUserAction(@PathVariable("id") int id,
			Map<String, String> message) {
		
		try {
			service.deleteUser(id);
			message.put("message", "User was successfully deleted");
		} 
		catch (Exception ex) {
			message.put("message", "Some problem occured! User was not deleted");
		}
		
		return message;
	}
}
