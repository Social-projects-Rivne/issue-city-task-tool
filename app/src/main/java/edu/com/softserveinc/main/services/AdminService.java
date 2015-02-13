package edu.com.softserveinc.main.services;

//import org.hibernate.Session;
import java.util.List;

import edu.com.softserveinc.main.models.UserModel;

/**
 * 
 * Admin model class
 * 
 * @author nazar
 *
 */
public class AdminService implements UserService {

	/**
	 * Add new user in table
	 * 
	 * @param user
	 */
	@Override
	public void addUser(UserModel user) {

		new UserServiceImpl().addUser(user);
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
			new UserServiceImpl().editUser(user);
		}
	}

	/**
	 * Delete user from table
	 * 
	 * @param userId
	 */
	@Override
	public void deleteUser(UserModel user) {
		
		new UserServiceImpl().deleteUser(user);

	}

	/**
	 * It returns user by his id
	 * 
	 * @param userId
	 */
	@Override
	public UserModel getUserByID(int userId) {

		return new UserServiceImpl().getUserByID(userId);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List loadUsersList() {
		
		return new UserServiceImpl().loadUsersList();
	}

}
