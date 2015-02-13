package edu.com.softserveinc.main.services;

import java.util.List;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.utils.PasswordEncoder;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser(UserModel user) {
		// Encode password in SHA-512
		if (user.getPassword().length() < 61)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		
		new DaoImpl().addInDB(user);
	}

	@Override
	public void deleteUser(UserModel user) {

		if (user.getId() != 0) {
			new DaoImpl().deleteFromDB(user);
		}
	}

	@Override
	public void editUser(UserModel user) {
		// Encode password in SHA-512
		if (user.getPassword().length() < 61)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		
		if (user.getId() != 0) {
			new DaoImpl().editInDB(user);
		}
	}

	@Override
	public UserModel getUserByID(int id) {
		return (UserModel) new DaoImpl().getById(id, new UserModel());
	}

	@SuppressWarnings("rawtypes")
	public List loadUsersList() {
		return new DaoImpl().getAll(new UserModel());
	}
}
