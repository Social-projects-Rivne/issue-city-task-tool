package edu.com.softserveinc.main.services;

import java.util.List;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.interfaces.UserService;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.utils.PasswordEncoder;

public class UserServiceImpl implements UserService {

	DaoImpl dao = new DaoImpl();
	
	/**
	 * add new user in DB
	 * @param user
	 */
	@Override
	public void addUser(UserModel user) {
		// Encode password in SHA-512
		if (user.getPassword().length() < 61)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		
		dao.addInDB(user);
	}

	/**
	 * delete user from DB 
	 * @param user 
	 */
	@Override
	public void deleteUser(UserModel user) {

		if (user.getId() != 0) {
			dao.deleteFromDB(user);
		}
	}

	/**
	 * update user fields in DB
	 * @param user
	 */
	@Override
	public void editUser(UserModel user) {
		// Encode password in SHA-512
		if (user.getPassword().length() < 61)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		
		if (user.getId() != 0) {
			dao.editInDB(user);
		}
	}

	/**
	 * Get user from DB by id  
	 * @param id
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel getUserByID(int id) {
		return (UserModel) dao.getById(id, new UserModel());
	}
	
	/**
	 * Return all users from DB in list
	 * @return {@link List} {@link UserModel}
	 */
	@SuppressWarnings("rawtypes")
	public List loadUsersList() {
		return dao.getAll(new UserModel());
	}
}
