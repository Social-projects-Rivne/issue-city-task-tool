package edu.com.softserveinc.main.services;

import java.util.List;

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
	public void deleteUser(UserModel user);

	/**
	 * Change existing user's fields in Data Base
	 * 
	 * @param user
	 */
	public void editUser(UserModel user);

	/**
	 * 
	 * Get user's fields from Data Base by user_id
	 *
	 */
	public UserModel getUserByID(int userId);
	
	/**
	 * Get all users from Data Base;
	 * 
	 * @param user
	 * @return list of all users
	 */
	@SuppressWarnings("rawtypes")
	public List loadUsersList();
}
