package edu.com.softserveinc.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.implementation.DeleteUser;

public class DeleteUserImpl implements DeleteUser {

	@Override
	public void deleteUser(int userId) {
		@SuppressWarnings("deprecation")
		GetUserByIdImpl getusr = new GetUserByIdImpl();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(getusr.getUserByID(userId));
		session.getTransaction().commit();
		session.close();

	}

}
