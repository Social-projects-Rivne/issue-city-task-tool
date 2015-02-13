package edu.com.softserveinc.main.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.CategoryModel;
import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.CategoryServiceImpl;
import edu.com.softserveinc.main.services.CommentServiceImpl;
import edu.com.softserveinc.main.services.IssueServiceImpl;
import edu.com.softserveinc.main.services.UserServiceImpl;
import edu.com.softserveinc.main.utils.IssueValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	// --------------------ISSUE METHODS-------------------//

	@RequestMapping("get-issue/{id}")
	public @ResponseBody IssueModel getIssue(@PathVariable("id") int id,
			IssueServiceImpl service) {
		return service.getByID(id);
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@RequestMapping("get-issues")
	public @ResponseBody List getIssues() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session.createQuery("From IssueModel").list();
	}

	@RequestMapping(value = "add-issue", method = RequestMethod.POST)
	public String addIssue(HttpServletRequest request, IssueServiceImpl service) {
		String mapPointer = request.getParameter("mapPointer");
		String issueName = request.getParameter("issueName");
		String issueCategoryName = request.getParameter("issueCategory");
		String issueDescription = request.getParameter("issueDescription");
		String issueAttachments = request.getParameter("issueAttachments");

		issueCategoryName.toLowerCase();
		CategoryModel category = new CategoryModel(issueCategoryName);

		try {
			new CategoryServiceImpl().addCategory(category);
			System.out.println("category created with id" + category.getId());
		}
		// org.hibernate.exception
		catch (Exception ex) {
			category = new CategoryServiceImpl()
					.getCategoryByName(issueCategoryName);
			System.out.println("category loaded");
		}
		IssueModel issue = new IssueModel(issueName,issueDescription, mapPointer, issueAttachments,  category.getId(),1,1);

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
		return "redirect:/";
	}

	// --------------------COMMENT METHODS-------------------//

	@SuppressWarnings("rawtypes")
	@RequestMapping("get-comments")
	public @ResponseBody List getCommentsByIssueId(
			@RequestParam("issueId") int id, CommentServiceImpl service) {
		return service.getCommentsByIssueId(id);
	}

	// fetch all comments for issue-id
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "all-comments/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List getAllCommentsByIssueId(@PathVariable int id) {
		return new CommentServiceImpl().getCommentsByIssueId(id);
	}

	// adding comment for issue
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "add-comment", method = RequestMethod.POST)
	public @ResponseBody java.util.LinkedHashMap createUser(
			@RequestBody final java.util.LinkedHashMap comment) {
		int id = Integer.parseInt(comment.get("issueId").toString());
		new CommentServiceImpl().addComment(new CommentModel(comment.get(
				"comment").toString(), comment.get("userName").toString(),
				comment.get("email").toString(), id));
		System.out.println("email: " + comment.get("email") + "issue id: "
				+ comment.get("issueId"));
		return comment;
	}

	// --------------------USER METHODS-------------------//

	@SuppressWarnings("rawtypes")
	@RequestMapping("get-users")
	public @ResponseBody List getUsers(UserServiceImpl service) {
		return service.loadUsersList();
	}

	@RequestMapping(value = "add-new-user", method = RequestMethod.POST)
	public @ResponseBody String addUser(@RequestBody UserModel user,
			UserServiceImpl service) {
		String message = null;

		try {
			service.addUser(user);
		} catch (Exception ex) {
		}

		return message;
	}

	@RequestMapping(value = "edit-user/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editUser(
			@ModelAttribute("user") UserModel user, UserServiceImpl service) {
		String message = null;

		try {
		} catch (Exception ex) {
		}

		return message;
	}

	@RequestMapping(value = "remove-user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String removeUser(@PathVariable("id") int id,
			UserServiceImpl service) {
		String message = null;

		try {
			service.deleteUser(service.getUserByID(id));
		} catch (Exception ex) {
		}

		return message;
	}
}
