package edu.com.softserveinc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.com.softserveinc.main.dao.LoadUsersListImpl;
import edu.com.softserveinc.main.dao.UserServiceImpl;
import edu.com.softserveinc.main.models.UserModel;

@Controller
public class BawlController {
	
	@RequestMapping(value = "/admin-toolpage")
	public String showUsersTable(LoadUsersListImpl usersList, Model model) {
	    model.addAttribute("users", usersList.loadUsersList());
	    
		return "admin-toolpage";
	}
	
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") UserModel user, UserServiceImpl userService, Model model) {

		userService.addUser(user);
		model.addAttribute("notification", "New user was succesfully added!");
		
		return "redirect:admin-toolpage";
	}
	
	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") UserModel user, UserServiceImpl userService, Model model) {
		
		userService.editUser(user);
		model.addAttribute("notification", "User was succesfully edited!");
		
		return "redirect:admin-toolpage";
	}
	
	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
	public String removeUser(@RequestParam("userId") int userId, UserServiceImpl userService, Model model) {
		
		userService.deleteUser(userId);
		model.addAttribute("notification", "User was succesfully removed!");
		
		return "redirect:admin-toolpage";
	}
	
}
