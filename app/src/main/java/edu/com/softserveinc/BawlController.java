package edu.com.softserveinc;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.com.softserveinc.main.dao.users.GetUserByIdImpl;
import edu.com.softserveinc.main.dao.users.LoadUsersListImpl;
import edu.com.softserveinc.main.dao.users.UserServiceImpl;
import edu.com.softserveinc.main.models.UserModel;

@Controller
public class BawlController {

	// TODO: change "UserServiceImpl" on "AdminService"
	@RequestMapping(value = "/admin-toolpage")
	public String showUsersTable(LoadUsersListImpl usersList, Model model) {
		try {
			model.addAttribute("users", usersList.loadUsersList());
		} catch (JDBCConnectionException ex) {

			// TODO: Change it on logger!
			System.out
					.println("ERROR! Can't connect to database, try to change "
							+ "your login and password from MySQL-server in hibernata.cfg.xml");

			return "error";
		}
		return "admin-toolpage";
	}

	// TODO: change "UserServiceImpl" on "AdminService"
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") UserModel user,
			UserServiceImpl userService, Model model) {

		try {
			userService.addUser(user);
			model.addAttribute("notification",
					"New user have succesfully added!");
		} catch (Exception ex) {
			model.addAttribute("notification", "Error " + ex.toString());
		}
		return "redirect:admin-toolpage";
	}

	// TODO: change "UserServiceImpl" on "AdminService"
	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") UserModel user,
			UserServiceImpl userService, Model model) {
		try {
			userService.editUser(user);
			model.addAttribute("notification", "User have succesfully edited!");
		} catch (Exception ex) {
			model.addAttribute("notification", "User exists!");
		}
		return "redirect:admin-toolpage";
	}

	// TODO: change "UserServiceImpl" on "AdminService"
	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
	public String removeUser(@RequestParam("userId") int userId,
			UserServiceImpl userService, GetUserByIdImpl getUsr, Model model) {

		try {
			userService.deleteUser(getUsr.getUserByID(userId));
			model.addAttribute("notification", "User have succesfully removed!");
		} catch (Exception ex) {
			model.addAttribute("notification", "Erro! " + ex.toString());
		}
		return "redirect:admin-toolpage";
	}
	
	@ModelAttribute
	public void addString(Model model) {
		model.addAttribute("notMsg", notificationMessage);
	}

}
