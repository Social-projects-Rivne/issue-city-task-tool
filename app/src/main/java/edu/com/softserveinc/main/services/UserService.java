package edu.com.softserveinc.main.services;

import java.util.List;

import edu.com.softserveinc.main.models.UserModel;

public interface UserService {

	public void addUser(UserModel user);

	public void editUser(UserModel user);
	
	public void deleteUser(UserModel user);
	
	public void deleteUser(int userId);

	public UserModel getUserByID(int userId);
	
	public List<UserModel> loadUsersList();
	
	public UserModel getUserByName(String name) throws Exception;
}
