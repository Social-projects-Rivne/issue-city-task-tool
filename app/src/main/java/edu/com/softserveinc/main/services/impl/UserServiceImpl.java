package edu.com.softserveinc.main.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.main.dao.UserDao;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDao userDao;
	
	@Override
	@Transactional
	public void addUser(UserModel user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.saveAndFlush(user);
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		if (id != 0) {
			userDao.delete(id);
		}
	}

	@Override
	@Transactional
	public void editUser(UserModel user) {
		if (user.getPassword() == "_")
			user.setPassword(userDao.findOne(user.getId()).getPassword());
		else 
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.saveAndFlush(user);
	}

	@Override
	@Transactional
	public UserModel getById(int id) {
		return userDao.findOne(id);
	}
	
	@Override
//	BUG: Sets password in DB
//	@Transactional
	public Collection<UserModel> loadUsersList() {
		Collection<UserModel> users = userDao.findAll();
		for (UserModel user: users){
			user.setPassword("_");
		}
		return users;
	}

}
