package edu.com.softserveinc.main.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.dao.UserExist;
import edu.com.softserveinc.main.models.UserModel;

public class AddUserIml implements AddUser{

	@Override
	public void addUser(UserModel user) {
		

		if(new UserExist(user.getName()).isNotExist())
		{
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
		}

	}
	

}
