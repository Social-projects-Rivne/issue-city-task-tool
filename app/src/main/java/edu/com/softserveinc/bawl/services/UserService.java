package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;

import java.util.List;

public interface UserService {

	public UserModel addUser(UserModel user);

	public void editUser(UserModel user);

	public void editUserPass(UserModel user);
	
	public void deleteUser(int userId);
	
	public UserModel getById(int id);

	public List<UserModel> getByRoleId (UserRole userRole);
	
	public UserModel getByLogin(String login);

	public List<UserModel> loadUsersList();

}
