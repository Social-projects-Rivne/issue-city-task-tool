package edu.com.softserveinc.main.services;

import java.util.Collection;

import edu.com.softserveinc.main.models.UserModel;

public interface UserService {

	public void addUser(UserModel user);

	public void editUser(UserModel user);
	
	public void deleteUser(int userId);
	
	public UserModel getById(int id);

	public Collection<UserModel> loadUsersList();

}
