package edu.com.softserveinc.main.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.interfaces.LoadUsersList;

public class LoadUsersListImpl implements LoadUsersList{

	@SuppressWarnings("rawtypes")
	@Override
	public List loadUsersList() {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session.createQuery("FROM UserModel").list();
	}

}
