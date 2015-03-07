package edu.com.softserveinc.main.services.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.main.dao.UserDao;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDao userDao;
	
	@Override
	public void addUser(UserModel user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.saveAndFlush(user);
	}

	@Override
	public void deleteUser(int id) {
		if (id != 0) {
			userDao.delete(id);
		}
	}

	@Override
	public void editUser(UserModel user) {
		if (Arrays.asList("_", "", null).contains(user.getPassword())) {
			user.setPassword(userDao.findOne(user.getId()).getPassword());
		}
		else 
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.saveAndFlush(user);
	}

	@Override
	public UserModel getById(int id) {
		return userDao.findOne(id);
	}
	
	@Override
	public Collection<UserModel> loadUsersList() {
		return userDao.findAll();
	}

	@Override
	public UserModel getByLogin(String login) {
		return userDao.findByLogin(login);
	}

}
