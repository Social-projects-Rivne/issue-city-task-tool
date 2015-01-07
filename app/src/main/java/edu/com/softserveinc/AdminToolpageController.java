package edu.com.softserveinc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.com.softserveinc.main.dao.users.LoadUsersListImpl;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.utils.QueryBuilder;

import java.util.List; 

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Controller
public class AdminToolpageController {
	@RequestMapping(value = "/admin-toolpage", method = RequestMethod.GET)
	public String _method(Model model) {
	    model.addAttribute("Users", new LoadUsersListImpl().loadUsersList());
		return "admin-toolpage";
	}
}
