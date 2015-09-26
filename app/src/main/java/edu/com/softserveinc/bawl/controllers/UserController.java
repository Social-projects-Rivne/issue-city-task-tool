package edu.com.softserveinc.bawl.controllers;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import edu.com.softserveinc.bawl.services.MandrillMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.UserService;

import javax.persistence.Convert;
import javax.persistence.Converter;

@Controller
public class UserController {
	private final static int USER_NOT_CONFIRMED = -1;
	private final static int USER = 0;

	@Autowired
	private UserService service;

	@Autowired
	private MailService mailService;

	@RequestMapping("get-users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody Collection<UserModel> getUsersAction() {
		
		Collection<UserModel> users = service.loadUsersList();
		for (UserModel user: users){
			user.setPassword("_");
		}
		return users;
	}

	private String getRoleName(int role_id) {
		switch (role_id) {
			case 1:
				return "Admin";

			case 2:
				return "Manager";

			case 3:
				return "User";

			default:
				return "Not confirmed";
		}
	}


	
	
	@RequestMapping(value = "user", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> addUserAction(
			@RequestBody UserModel user, Map<String, String> message) {
		try {
			String role = getRoleName(user.getRole_id());
			if (role.equals("Admin") || role.equals("Manager"))
			{
				service.addUser(user);

				/*mailService.notifyUser(user.getId(),
						"Your account has been created.\n\nCurrent login: "
								+ user.getLogin() + "\nCurrent role: " + role);*/
				message.put("message", "User was successfully added");
			}

			else if (role.equals ("Not confirmed")) {
				service.addUser(user);
				UserModel dbModel = service.getByLogin(user.getLogin());

				message.put("message", "Successfully registered. Please confirm your email");
				try {
					MandrillMailService mailService = MandrillMailService.getMandrillMail();
					mailService.sendRegNotification(dbModel);
				} catch(Exception ex){
					message.put("message", "Something wrong with sending email");
				}
			}
		} catch (Exception ex) {
			message.put("message", "Some problem occured! User was not added");
		}

		return message;
	}



	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	//@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_ADMIN'")
	public @ResponseBody Map<String, String> editUserAction(
			@RequestBody UserModel user, Map<String, String> message) {

		try {
			service.editUser(user);
			/*String role = user.getRole_id() == 1 ? "Admin" : "Manager";
			mailService.notifyUser(user.getId(),
					"Your account has been updated.\n\nCurrent login: "
							+ user.getLogin() + "\nCurrent role: " + role);*/
			message.put("message", "User was successfully edited");
		} catch (Exception ex) {
			message.put("message", "Some problem occurred! User was not updated" + ex.toString());
		}

		return message;
	}

	@RequestMapping(value = "validate-user", method = RequestMethod.POST)
	public @ResponseBody UserModel validateUser(
			@RequestBody UserModel user) {
		try {

			UserModel dbModel = service.getById(user.getId());
			if (dbModel.getPassword().equals(user.getPassword())){
				dbModel.setRole_id(USER);
				service.editUser(dbModel);
				return dbModel;
			}
			//message.put("message", "User was successfully validated");
		} catch (Exception ex) {
			//message.put("message", "Some problem occurred! User was not validated" + ex.toString());
		}
		return null;

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
		
	@RequestMapping(value = "currentuser", method = RequestMethod.GET)
	public @ResponseBody UserModel getCurrentUserAction(){
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (currentUserLoginName.equals("anonymousUser")) {
			return null;
		}
		else {
			return service.getByLogin(currentUserLoginName);
		}
	}
}
