package edu.com.softserveinc.main.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.CategoryModel;
import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.services.CategoryServiceImpl;
import edu.com.softserveinc.main.services.CommentServiceImpl;
import edu.com.softserveinc.main.services.IssueServiceImpl;
import edu.com.softserveinc.main.utils.IssueValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, CategoryServiceImpl service) {
		List categoriesList = service.loadCategoriesList();
		model.addAttribute("categories", categoriesList);
		return "home";
	}
	
	//add new problem
	@RequestMapping(value = "add-issue", method = RequestMethod.POST)
	public String addIssue(HttpServletRequest request, IssueServiceImpl service) {
		String mapPointer = request.getParameter("mapPointer");
		String issueName = request.getParameter("issueName");
		String issueCategoryName = request.getParameter("issueCategory");
		String issueDescription = request.getParameter("issueDescription");
		String issueAttachments = request.getParameter("issueAttachments");
		
		
		
		issueCategoryName.toLowerCase();
		CategoryModel category = new CategoryModel(issueCategoryName);
		
		try{
			 new CategoryServiceImpl().addCategory(category);
			 System.out.println("category created with id"+category.getId());
		}
		//org.hibernate.exception
		catch(Exception ex){
			category = new CategoryServiceImpl().getCategoryByName(issueCategoryName);
			System.out.println("category loaded");
		}
		
		IssueModel issue = new IssueModel(category.getId(), issueName,
				issueDescription, mapPointer, issueAttachments, 1);
		
		
		if (new IssueValidator(issue).isValid()) {
			try {
				service.addProblemm(issue);
				System.out.println("MAP POINTER +++++++++++  = " + mapPointer);
			} catch (Exception ex) {
				System.out.println("ERROR! Issue is not valid!!!!! "
						+ ex.toString());
			}
		} else {

			System.out.println("Error! Issue is not valid!!!");
		}
		// TODO: add here notification method!
		return "home";
	}

	@RequestMapping(value = "get-issue", method = RequestMethod.POST)
	public @ResponseBody IssueModel getIssue(
			@RequestBody Map<String, Object> request,
			IssueServiceImpl issueService) {

		System.out.println(request.get("id"));

		IssueModel issue = new IssueModel();
		issue.setId(1);

		return issue;
	}

	// fetch all comments for issue-id
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "all-comments/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List getAllCommentsByIssueId(@PathVariable int id) {
		return new CommentServiceImpl().getCommentsByIssueId(id);
	}

	// adding comment for issue
	@RequestMapping(value = "add-comment", method = RequestMethod.POST)
	public @ResponseBody CommentModel addComment(
			@RequestBody final CommentModel comment) {

		new CommentServiceImpl().addComment(comment);
		return comment;
	}

	@RequestMapping(value = "add-comment", method = RequestMethod.PUT)
	public @ResponseBody CommentModel updateComment(
			@RequestBody final CommentModel comment) {

		new CommentServiceImpl().addComment(comment);
		return comment;
	}

	@RequestMapping("get-markers")
	public @ResponseBody List getMarkers() {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session.createQuery("From IssueModel").list();
	}
}
