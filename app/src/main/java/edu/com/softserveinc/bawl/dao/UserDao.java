package edu.com.softserveinc.bawl.dao;

import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<UserModel, Integer>{

	UserModel findByLogin(String login);

	UserModel findIDByEmail (String email);

	UserModel findByEmail(String email);

	List<UserModel> findByRole(UserRole role);

}