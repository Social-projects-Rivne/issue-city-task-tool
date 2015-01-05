package edu.com.softserveinc.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.implementation.LoadUsersList;

public class LoadUsersListImpl implements LoadUsersList{

	@Override
	public List loadUsersList() {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session.createQuery("FROM UserModel").list();
	}

}
