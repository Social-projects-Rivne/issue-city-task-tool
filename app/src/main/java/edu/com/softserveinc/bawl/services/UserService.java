package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.UserModel;

import java.util.List;

public interface UserService {

	public UserModel addUser(UserModel user);

	public void editUser(UserModel user);

	public void editUserPass(UserModel user);
	
	public void deleteUser(int userId);
	
	public UserModel getById(int id);
	
	public UserModel getByLogin(String login);

	public List<UserModel> loadUsersList();

	public boolean isExistingUser(String Email);
}
