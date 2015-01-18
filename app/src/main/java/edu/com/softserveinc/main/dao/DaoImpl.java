package edu.com.softserveinc.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.interfaces.Dao;

public class DaoImpl implements Dao {

	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public DaoImpl() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	
	/**
	 * <h1>Method for adding object in database</h1>
	 * Method is untested! 
	 */
	@Override
	public void addInDB(Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * <h1>Method for editing object in database</h1>
	 * Method is untested! 
	 */
	@Override
	public void editInDB(Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		session.close();
	}
	
	
	//TODO: Test it MANUAL!!! 
	/**
	 * <h1>Method for editing object in database</h1>
	 * Method is untested! 
	 */
	@Override
	public void deleteFromDB(Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
		session.close();
	}

}
