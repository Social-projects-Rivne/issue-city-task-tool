package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;

import java.util.List;

public interface UserService {

	UserModel addUser(UserModel user);

	void editUser(UserModel user);

	void editUserPass(UserModel user);
	
	void deleteUser(int userId);
	
	UserModel getById(int id);

	List<UserModel> getByRoleId (UserRole userRole);
	
	UserModel getByLogin(String login);

	List<UserModel> loadUsersList();

	boolean isExistingUser(String Email);

	int getCurrentUserId();

	UserModel getCurrentUser();

	int getRole(String email);

	public UserModel getUserIdByEmail(String email);

	public  boolean isValidUser(String email);
}
