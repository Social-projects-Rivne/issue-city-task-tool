package edu.com.softserveinc.main.controllers;

import java.util.List;

import org.hibernate.JDBCException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.UserServiceImpl;

@Controller
public class AdminController {
	
	@RequestMapping("admin")
	public String admin() {
		return "admin";
	}

	// TODO: change "UserServiceImpl" on "AdminService"
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") UserModel user,
			UserServiceImpl userService, Model model) {
		try {
			userService.addUser(user);
		}
		catch (Exception ex) {
		}
		return "redirect:admin-toolpage";
	}

	// TODO: change "UserServiceImpl" on "AdminService"
	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public String editUser(@RequestParam("userId") int userId,
			@RequestParam("change_firstname") String name,
			@RequestParam("change_email") String email,
			@RequestParam("change_login") String login,
			UserServiceImpl userService, UserServiceImpl getUsr,
			UserModel user, Model model) {
		try {
		} 
		catch (JDBCException ex) {
		}
		catch (Exception ex) {
		}
		return "redirect:admin-toolpage";
	}

	// TODO: change "UserServiceImpl" on "AdminService"
	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
	public String removeUser(@RequestParam("userId") int userId,
			UserServiceImpl userService, UserServiceImpl getUsr, Model model) {
		try {

			userService.deleteUser(getUsr.getUserByID(userId));
		} catch (Exception ex) {
		}
		return "redirect:admin-toolpage";
	}

}