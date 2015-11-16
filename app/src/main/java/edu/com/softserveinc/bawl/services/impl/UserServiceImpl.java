package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.UserDao;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    public static final Logger LOG=Logger.getLogger(UserServiceImpl.class);
    public static final String ANONYMOUS_USER = "anonymousUser";

    @Autowired
    private UserDao userDao;
	
	@Override
	public UserModel addUser(UserModel user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setRole(UserRole.USER_NOT_CONFIRMED);
		return userDao.saveAndFlush(user);
	}

	@Override
	public void deleteUser(int id) {
		if (id != 0) {
			userDao.delete(id);
		}
	}

	@Override
	public void editUserPass(UserModel user) {
	/*	if (Arrays.asList("_", "", null).contains(user.getPassword())) {
			user.setPassword(userDao.findOne(user.getId()).getPassword());
		}
		else */
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		userDao.saveAndFlush(user);
	}

	@Override
	public boolean isExistingUser(String email){
		if( userDao.findByEmail(email).equals(email)){
			return true;
		} else return false;
	}

	@Override
	public int getRole(String email){
		UserServiceImpl userService = new UserServiceImpl();
		int role = userService.getUserIdByEmail(email).getRole().getId();
		return role;
	}

	@Override
	public UserModel getUserIdByEmail(String email){
		//UserModel userModel;
		return userDao.findIDByEmail(email);

	}

	@Override
	public void editUser(UserModel user) {
		userDao.saveAndFlush(user);
	}

	@Override
	public UserModel getById(int id) {
		return userDao.findOne(id);
	}

	@Override
	public List<UserModel> getByRoleId(UserRole userRole) {
		return userDao.findByRole(userRole);

	}

	@Override
	public List<UserModel> loadUsersList() {
		return userDao.findAll();
	}

	@Override
	public UserModel getByLogin(String login) {
		return userDao.findByLogin(login);
	}

	@Override
	public  boolean isValidUser(String email) {
		boolean role =  userDao.findIDByEmail(email).equals(email);
		return role;
	}

    @Override
    public int getCurrentUserId() {
        return getCurrentUser().getId();
    }

    @Override
    public UserModel getCurrentUser() {
        String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ANONYMOUS_USER.equals(currentUserLoginName)) {
            return null;
        } else {
            return getByLogin(currentUserLoginName);
        }
    }

}
