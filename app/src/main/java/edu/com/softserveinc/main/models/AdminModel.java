package edu.com.softserveinc.main.models;

import edu.com.softserveinc.main.dao.UserExist;
import edu.com.softserveinc.main.implementation.AddUser;



/**
 * 
 * Admin model class
 * 
 * @author nazar
 *
 */
public class AdminModel implements AddUser{


/**
 * Add new user in table 
 * @param user
 */
	@Override // from
	public void addUser(UserModel user) {
		
		if(new UserExist(user).isNotExist())
		{
			//TODO:add code with hibernate which will be adding new users in table
		
		
		}
	}

}
