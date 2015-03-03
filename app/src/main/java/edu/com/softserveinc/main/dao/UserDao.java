package edu.com.softserveinc.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.main.models.UserModel;
 
public interface UserDao extends JpaRepository<UserModel, Integer>{

	UserModel findByName(String name);
	
	UserModel findByLogin(String login);
	
}