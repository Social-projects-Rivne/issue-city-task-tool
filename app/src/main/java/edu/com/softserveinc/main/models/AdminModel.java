package edu.com.softserveinc.main.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.dao.UserExist;
import edu.com.softserveinc.main.implementation.AddUser;



/**
 * 
 * Admin model class
 * 
 * @author nazar
 *
 */
public class AdminModel implements AddUser{


/**
 * Add new user in table 
 * @param user
 */
	@Autowired // I dont know why in need 
	@Override // from
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
