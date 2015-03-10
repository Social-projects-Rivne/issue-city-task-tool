package edu.com.softserveinc.bawl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.UserModel;
 
public interface UserDao extends JpaRepository<UserModel, Integer>{

	UserModel findByLogin(String login);
	
}