package edu.com.softserveinc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.com.softserveinc.main.models.UserModel;
import java.util.List; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Controller
public class AdminToolpageController {
	private static SessionFactory factory;
	
	@RequestMapping(value = "/admin-toolpage", method = RequestMethod.GET)
	public String _method(Model model) {
	    factory = new AnnotationConfiguration().configure().addAnnotatedClass(UserModel.class).buildSessionFactory();
		Session session = factory.openSession();
	    Transaction tx = null;
	    tx = session.beginTransaction();
	    List users = session.createQuery("FROM UserModel").list();
	    model.addAttribute("Users", users);
	    tx.commit();
	    session.close();
		
		return "admin-toolpage";
	}
}
