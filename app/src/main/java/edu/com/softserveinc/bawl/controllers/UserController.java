package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.UserNotificationDto;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

//import java.util.logging.Logger;

@Controller
public class UserController {

	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(UserController.class);
	
	private final static int USER_NOT_CONFIRMED = -1;
	private final static int USER = 0;

	@Autowired
	private UserService userService;

	@RequestMapping("get-users")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody Collection<UserModel> getUsersAction() {
		
		Collection<UserModel> users = userService.loadUsersList();
		for (UserModel user: users){
			user.setPassword("_");
		}
		return users;
	}

	private String getRoleName(int role_id) {
		switch (role_id) {
			case 1: return "Admin";
			case 2: return "Manager";
			case 3: return "User";
			default: return "Not confirmed";
		}
	}
	@RequestMapping(value = "user", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> addUserAction(
			@RequestBody UserModel user, Map<String, String> message) {
		try {

			userService.addUser(user);
			UserModel dbModel = userService.getByLogin(user.getLogin());

			message.put("message", "Successfully registered. Please confirm your email");
			try {
				MandrillMailServiceImpl.getMandrillMail().sendRegNotification(dbModel);
			} catch(Exception ex){
				message.put("message", "Something wrong with sending email");
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
			userService.editUser(user);
			String role = getRoleName (user.getRole_id());
			MandrillMailServiceImpl.getMandrillMail().notifyUser(user.getId(),
					"Your account has been updated.\n\nCurrent login: "
							+ user.getLogin() + "\nCurrent role: " + role);
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

			UserModel dbModel = userService.getById(user.getId());
			if (dbModel.getPassword().equals(user.getPassword())){
				dbModel.setRole_id(USER);
				userService.editUser(dbModel);
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
			userService.deleteUser(id);
			MandrillMailServiceImpl.getMandrillMail().notifyUser(id, "Your account has been terminated.");
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
			return userService.getByLogin(currentUserLoginName);
		}
	}

	 /* This metod send notification to email from #admin panel */
	@RequestMapping(value="send-notification", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> submittedFromData(@RequestBody UserNotificationDto userNotificationModel, Map<String, String> message) {
		try { MandrillMailServiceImpl.getMandrillMail().notifyByAdmin(userNotificationModel.getEmail(),
				userNotificationModel.getSubject(),userNotificationModel.getMessage() );
			  message.put("message", "Message was successfully sended");
		}catch (Exception ex) { message.put("message", "Some problem occured! Message was not sended");
			}
		return message ;
	}

	@RequestMapping(value="user/{id}/changepass", method = RequestMethod.GET)
	public @ResponseBody String changeUserPassword(@PathVariable int id){
		UserModel userModel=userService.getById(id);

		String newPassword = generatePassword(1, 5);
		userModel.setPassword(newPassword);

		MandrillMailServiceImpl.getMandrillMail().sendPasswordToUser(userModel, newPassword);

		String massege="Your pass have been changed ! Watch about it on your mail ! 'pass'="+newPassword;//later it will be whithout password

		userService.editUser(userModel);

		return massege;
	}

	private String generatePassword (int from, int to){

		String pass  = "";
		Random r     = new Random();
		int cntchars = from + r.nextInt(to - from + 1);

		for (int i = 0; i < cntchars; ++i) {
			char next = 0;
			int range = 10;

			switch(r.nextInt(3)) {
				case 0: {next = '0'; range = 10;} break;
				case 1: {next = 'a'; range = 26;} break;
				case 2: {next = 'A'; range = 26;} break;
			}

			pass += (char)((r.nextInt(range)) + next);
		}

		return pass;
	}
}






