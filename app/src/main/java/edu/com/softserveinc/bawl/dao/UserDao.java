package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserDao extends JpaRepository<UserModel, Integer>{

	UserModel findByLogin(String login);

	Collection <UserModel> findByEmail(String email);
	
}