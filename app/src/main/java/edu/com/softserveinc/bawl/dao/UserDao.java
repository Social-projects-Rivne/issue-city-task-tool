package edu.com.softserveinc.bawl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.com.softserveinc.bawl.models.UserModel;

import java.util.List;

import java.util.Collection;

public interface UserDao extends JpaRepository<UserModel, Integer>{

	UserModel findByLogin(String login);

	Collection <UserModel> findByEmail(String email);
	
}