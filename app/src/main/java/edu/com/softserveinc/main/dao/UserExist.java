package edu.com.softserveinc.main.dao;

import edu.com.softserveinc.main.models.UserModel;

/**
 * It checks is  existing user in DataBase 
 * @author nazar
 */
public class UserExist{
	
	private String name; //The name which will be checking
	
	
	//Constructors
	/**
	 * 
	 * @param user
	 */
	public UserExist(UserModel user){
		 this.name = user.getName();
	}

	/**
	 * 
	 * @param name
	 */
	public UserExist(String name){
		 this.name = name;
	}
	
	//Methods
	/**
	 * Method for checking user existence
	 * 
	 *  @return true, if user is NOT exist in table, and false, if it is exist in table  
	 */
	public boolean isNotExist(){
		if (name!="") {
			return true;
		}
		else 
			return false;
	}
}
