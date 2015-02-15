package edu.com.softserveinc.main.services;

//import org.hibernate.Session;
import java.util.List;

import edu.com.softserveinc.main.interfaces.UserService;
import edu.com.softserveinc.main.models.UserModel;

/**
 * 
 * Admin model class
 * 
 * @author nazar
 *
 */
public class AdminService implements UserService {
	
	private UserServiceImpl userService = new UserServiceImpl();
	
	/**
	 * Add new user in table
	 * 
	 * @param user
	 */
	@Override
	public void addUser(UserModel user) {

		userService.addUser(user);
	}

	/**
	 * <h1>Method for editing users</h1> <b> Very important!! Check that user id
	 * is not Null! </b>
	 * 
	 * @param user
	 */
	@Override
	public void editUser(UserModel user) {
		if (user.getId() != 0) {
			userService.editUser(user);
		}
	}

	/**
	 * Delete user from table
	 * 
	 * @param userId
	 */
	@Override
	public void deleteUser(UserModel user) {
		
		userService.deleteUser(user);

	}

	/**
	 * It returns user by his id
	 * 
	 * @param userId
	 */
	@Override
	public UserModel getUserByID(int userId) {

		return userService.getUserByID(userId);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List loadUsersList() {
		
		return userService.loadUsersList();
	}

}
