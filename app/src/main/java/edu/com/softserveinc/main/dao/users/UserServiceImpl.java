package edu.com.softserveinc.main.dao.users;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.implementation.UserService;
import edu.com.softserveinc.main.models.UserModel;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser(UserModel user) {
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
		if (user.getId() != 0) {
			new DaoImpl().editInDB(user);
		}
	}

}
