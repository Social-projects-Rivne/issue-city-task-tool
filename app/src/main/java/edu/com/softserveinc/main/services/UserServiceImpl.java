package edu.com.softserveinc.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import edu.com.softserveinc.main.dao.UserDao;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.utils.PasswordEncoder;

public class UserServiceImpl implements UserService {

	@Autowired
    private UserDao userDao;
	
	@Override
	public void addUser(UserModel user) {
		// Encode password in SHA-512
		if (user.getPassword().length() < 61)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		
		userDao.saveAndFlush(user);
	}

	@Override
	public void deleteUser(UserModel user) {

		if (user.getId() != 0) {
			userDao.delete(user);
		}
	}

	@Override
	public void editUser(UserModel user) {
		// Encode password in SHA-512
		if (user.getPassword().length() < 61)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		
		if (user.getId() != 0) {
			userDao.saveAndFlush(user);
		}
	}

	@Override
	public UserModel getUserByID(int id) {
		return userDao.findOne(id);
	}
	
	@SuppressWarnings("rawtypes")
	public List loadUsersList() {
		return userDao.findAll();
	}
}
