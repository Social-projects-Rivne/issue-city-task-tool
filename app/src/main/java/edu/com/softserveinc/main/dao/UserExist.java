package edu.com.softserveinc.main.dao;

import java.util.List; 

//import org.hibernate.HibernateException; 
import org.hibernate.Query;
import org.hibernate.Session; 
//import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.com.softserveinc.main.models.UserModel;
//TODO: Fix it later. Now we are checking  user existing via try-catch 
/**
 * It checks is  existing user in DataBase 
 * @author nazar
 */
public class UserExist{
	
	private String name; //The name which will be checking
	private QueryBuilder queryBuilder = new QueryBuilder();
	
	//Constructors
	/**
	 * 
	 * @param user
	 */
	public UserExist(UserModel user){
		 this.name = user.getName();
	}

	/**
	 * 
	 * @param name
	 */
	public UserExist(String name){
		 this.name = name;
	}
	
	//Methods
	/**
	 * Method for checking user existence
	 * 
	 *  @return true, if user is NOT exist in table, and false, if it is exist in table  
	 */
	
	// it works wrong :-( 
	public boolean isNotExist(){ //throws HibernateException
		try{
			//TODO: create global session factory and remove this one
			//open new session
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			queryBuilder.column("id, avatar_url, role_id, email, name, login, password")
				.from("users").where("login = '" + name + "'");
			String hql = queryBuilder.toString();
			
			System.out.println("=============== Builder ============== Builded query: "
					+queryBuilder.toString());
			
			//query to db
			//String hql = "SELECT * FROM users WHERE name = " + this.name;
			
			Query query = session.createQuery(hql);
			
			System.out.println("=============== Query succes ==============");
			
			session.close();
			
			UserModel user = null;
			user.setName(name);
			
			List<UserModel> results = session.createQuery(hql).list();

			//TODO: change it on logger
			System.out.print("SUCCESS");
			
			if (results.size() == 0) {
				return true;
			}
			else 
				return false;
			
		}
		catch(Exception e){
			
			//TODO: change it on logger
			System.out.println("===============ERROR==============");
			
			return true; 
		}
	}
}
