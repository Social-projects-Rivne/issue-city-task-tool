package edu.com.softserveinc.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.implementation.AddUser;
import edu.com.softserveinc.main.models.UserModel;

public class AddUserImpl implements AddUser{

	@Override
	public void addUser(UserModel user) {
		


		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		//we have exception for this checking
		/*
		   if(new UserExist(user.getLogin()).isNotExist())
				
			{	
					
			}
		*/

	}
	

}
