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

    public static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
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
	public UserModel addSubscriber(UserModel user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setRole(UserRole.SUBSCRIBER);
		return userDao.saveAndFlush(user);
	}

	@Override
	public void deleteUser(int id) {
		UserModel userModel = getById(id);
		userModel.setRole(UserRole.DELETED);
		userDao.saveAndFlush(userModel);
		}

	@Override
	public void editUserPass(UserModel user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDao.saveAndFlush(user);
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
		boolean role;
		try {
			if (userDao.findByEmail(email) == null){
			role = false;
			}else role = true;
		}catch (Exception ex){
			LOG.warn(ex);
			role = false;
		}
		return role;
	}

    @Override
    public int getCurrentUserId() {
        return getCurrentUser().getId();
    }

	public int getUserIdByEmail(String email){
		return userDao.findByEmail(email).getId();
	}

	public String getAvatarByEmailOrDefault(String email) {
		UserModel user = userDao.findByEmail(email);
		if(user != null){
			return user.getAvatar();
		}
		return "";
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
