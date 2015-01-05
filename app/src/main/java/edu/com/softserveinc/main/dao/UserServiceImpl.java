package edu.com.softserveinc.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.implementation.UserService;
import edu.com.softserveinc.main.models.UserModel;

public class UserServiceImpl implements UserService{

	SessionFactory sessionFactory;
	
	
	@SuppressWarnings("deprecation")
	public UserServiceImpl() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	@Override
	public void addUser(UserModel user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteUser(int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(new GetUserByIdImpl()
			.getUserByID(userId)); // Take user by his id from table and than remove it
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void editUser(UserModel user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

}
