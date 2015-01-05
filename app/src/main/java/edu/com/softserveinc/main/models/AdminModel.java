package edu.com.softserveinc.main.models;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.dao.GetUserByIdImpl;
//import edu.com.softserveinc.main.dao.UserExist;
import edu.com.softserveinc.main.dao.UserServiceImpl;
import edu.com.softserveinc.main.implementation.GetUserByID;
import edu.com.softserveinc.main.implementation.UserService;

/**
 * 
 * Admin model class
 * 
 * @author nazar
 *
 */
public class AdminModel implements UserService, GetUserByID {

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
	public void deleteUser(int userId) {

		new UserServiceImpl().deleteUser(userId);

	}

	/**
	 * It returns user by his id
	 * 
	 * @param userId
	 */
	@Override
	public UserModel getUserByID(int userId) {

		return new GetUserByIdImpl().getUserByID(userId);

	}

}
