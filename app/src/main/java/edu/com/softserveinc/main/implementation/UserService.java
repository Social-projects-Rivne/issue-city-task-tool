package edu.com.softserveinc.main.implementation;

import edu.com.softserveinc.main.models.UserModel;

public interface UserService {

	/**
	 * Add user in table users
	 * 
	 * @param user
	 */
	public void addUser(UserModel user);

	/**
	 * Delete user from table
	 * 
	 * @param userId
	 */
	public void deleteUser(int userId);

	/**
	 * Change existing user's fields in Data Base
	 * 
	 * @param user
	 */
	public void editUser(UserModel user);

}
