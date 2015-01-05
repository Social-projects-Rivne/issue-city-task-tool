package edu.com.softserveinc.main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.implementation.EditUser;
import edu.com.softserveinc.main.models.UserModel;
/**
 * Class for editing user
 * 
 * @author nazar
 *
 */
public class EditUserImpl implements EditUser{
	/**
	 * <h1>Method for editing users</h1>
	 * <b> 
	 * 	Very important!! Check that user id is not Null! 
	 * </b>
	 * @param user
	 */
	@Override
	public void editUser(UserModel user) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

}
