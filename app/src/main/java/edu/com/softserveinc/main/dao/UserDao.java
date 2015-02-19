package edu.com.softserveinc.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.com.softserveinc.main.models.UserModel;
 
public interface UserDao extends JpaRepository<UserModel, Integer>{
	@Query("select users from UserModel users where users.name = :name")
	UserModel findByName(@Param("name") String name);
	
	@Query("select users from UserModel users where users.login = :login")
	UserModel findByLogin(@Param("name") String login);
	
}