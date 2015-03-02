package edu.com.softserveinc.main.controllers;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.MailService;
import edu.com.softserveinc.main.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private MailService mailService;

	@RequestMapping("get-users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody Collection<UserModel> getUsersAction() {
		return service.loadUsersList();
	}
	
	//testMethod
	@RequestMapping("mailuser/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody UserModel mailUserTestAction(@PathVariable int id) {
		UserModel user = service.getById(id);
		String role = user.getRole_id() == 1 ? "Admin" : "Manager";
		mailService.notifyUser(user.getId(),
				"Your account has been updated.\n\nCurrent login: "
						+ user.getLogin() + "\nCurrent role: " + role);
		return user;
	}
	
	@RequestMapping(value = "user", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody Map<String, String> addUserAction(
			@RequestBody UserModel user, Map<String, String> message) {

		try {
			service.addUser(user);
			String role = user.getRole_id() == 1 ? "Admin" : "Manager";
			mailService.notifyUser(user.getId(),
					"Your account has been created.\n\nCurrent login: "
							+ user.getLogin() + "\nCurrent role: " + role);
			message.put("message", "User was successfully added");
		} catch (Exception ex) {
			message.put("message", "Some problem occured! User was not added");
		}

		return message;
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody Map<String, String> editUserAction(
			@RequestBody UserModel user, Map<String, String> message) {

		try {
			service.editUser(user);
			String role = user.getRole_id() == 1 ? "Admin" : "Manager";
			mailService.notifyUser(user.getId(),
					"Your account has been updated.\n\nCurrent login: "
							+ user.getLogin() + "\nCurrent role: " + role);
			message.put("message", "User was successfully edited");
		} catch (Exception ex) {
			message.put("message", "Some problem occured! User was not edited");
		}

		return message;
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody Map<String, String> removeUserAction(
			@PathVariable int id, Map<String, String> message) {

		try {
			mailService.notifyUser(id, "Your account has been terminated.");
			service.deleteUser(id);
			message.put("message", "User was successfully deleted");
		} catch (Exception ex) {
			message.put("message", "Some problem occured! User was not deleted");
		}

		return message;
	}
}
