package edu.com.softserveinc.bawl.services;

import java.util.Collection;

import edu.com.softserveinc.bawl.models.UserModel;

public interface UserService {

	public void addUser(UserModel user);

	public void editUser(UserModel user);

	public void editUserPass(UserModel user);
	
	public void deleteUser(int userId);
	
	public UserModel getById(int id);
	
	public UserModel getByLogin(String login);

	public Collection<UserModel> loadUsersList();

}
