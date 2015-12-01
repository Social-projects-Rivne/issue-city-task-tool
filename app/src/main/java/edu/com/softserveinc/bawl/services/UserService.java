package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;

import java.util.List;

public interface UserService {

	UserModel addUser(UserModel user);

	void editUser(UserModel user);

	void editUserPass(UserModel user);
	
	void deleteUser(int id);
	
	UserModel getById(int id);

	List<UserModel> getByRoleId (UserRole userRole);

	UserModel getByLogin(String login);

	List<UserModel> loadUsersList();

	int getCurrentUserId();

	UserModel getCurrentUser();

	public  boolean isValidUser(String email);

	public int getUserIdByEmail(String email);

	public UserModel addSubscriber(UserModel user);

	public String getAvatarByEmailOrDefault(String email);

}
