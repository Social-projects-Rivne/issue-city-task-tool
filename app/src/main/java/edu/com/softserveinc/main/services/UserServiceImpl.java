package edu.com.softserveinc.main.services;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.interfaces.UserService;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.utils.PasswordEncoder;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser(UserModel user) {
		//Encode password
		if(user.getPassword().length() < 67)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		new DaoImpl().addInDB(user);
	}

	@Override
	public void deleteUser(UserModel user) {
		//Encode password
		if(user.getPassword().length() < 67)
			user.setPassword(new PasswordEncoder(user.getPassword()).encode());
		
		if (user.getId() != 0) {
			new DaoImpl().deleteFromDB(user);
		}
	}

	@Override
	public void editUser(UserModel user) {
		if (user.getId() != 0) {
			new DaoImpl().editInDB(user);
		}
	}

	@Override
	public UserModel getUserByID(int userId) {
	
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserModel user = (UserModel)session.get(UserModel.class, userId);
		session.close();
		return user;
	}
	

}
