package edu.com.softserveinc.main.dao.users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.implementation.GetUserByID;
import edu.com.softserveinc.main.models.UserModel;

public class GetUserByIdImpl implements GetUserByID {

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
