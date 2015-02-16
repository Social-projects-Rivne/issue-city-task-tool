package edu.com.softserveinc.main.controllers;

import java.util.List;
import java.util.Map;

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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	//--------------------ISSUE METHODS-------------------//
	
	@RequestMapping("issue/{id}")
	public @ResponseBody IssueModel getIssue(@PathVariable("id") int id,
			IssueServiceImpl service) {
		return service.getByID(id);
	}
	
	@RequestMapping(value = "delete-issue/{id}", method = RequestMethod.POST)
	public @ResponseBody void deleteIssue(@PathVariable("id") int id,
			IssueServiceImpl service) {
		IssueModel issue = service.getByID(id);
		//issue.setId(id);
		service.deletteProblemm(issue);
		System.out.print(id);
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
	
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue", method = RequestMethod.POST)
	public @ResponseBody String addIssue(@RequestBody Map request,
			IssueServiceImpl issueService, CategoryServiceImpl categoryService) {
		
		String message = null;
		String category = request.get("category").toString().toLowerCase();
		List categories = categoryService.loadCategoriesList();
		CategoryModel categoryModel = null;
		int categoryId = 0;
		
		for(int i = 0; i < categories.size(); i++) {
			categoryModel = (CategoryModel) categories.get(i);
			if(category.equals(categoryModel.getName())) {
				categoryId = categoryModel.getId();
				break;
			}
		}
		
		if(categoryId == 0) {
			categoryService.addCategory(new CategoryModel(category));
			categoryId = categoryService.getCategoryByName(category).getId();
		}
		
		IssueModel issue = new IssueModel(
				request.get("name").toString(),
				request.get("description").toString(),
				request.get("mapPointer").toString(),
				request.get("attachments").toString(),
				categoryId,
				Integer.parseInt(request.get("priorityId").toString()),
				Integer.parseInt(request.get("statusId").toString())
		);

		issueService.addProblemm(issue);		
		
		return message;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editIssue(@RequestBody Map request,
			IssueServiceImpl issueService, CategoryServiceImpl categoryService) {
		
		String message = null;
		String category = request.get("category").toString().toLowerCase();
		List categories = categoryService.loadCategoriesList();
		CategoryModel categoryModel = null;
		int categoryId = 0;
		
		for(int i = 0; i < categories.size(); i++) {
			categoryModel = (CategoryModel) categories.get(i);
			if(category.equals(categoryModel.getName())) {
				categoryId = categoryModel.getId();
				break;
			}
		}
		
		if(categoryId == 0) {
			categoryService.addCategory(new CategoryModel(category));
			categoryId = categoryService.getCategoryByName(category).getId();
		}
		
		IssueModel issue = issueService.getByID(Integer.parseInt(request.get("id").toString()));
		issue.setCategoryId(categoryId);

		issueService.editProblemm(issue);		
		
		return message;
	}
	
	
	//--------------------CATEGORY METHODS-------------------//
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("get-categories")
	public @ResponseBody List getCategories(CategoryServiceImpl service) {
		return service.loadCategoriesList();
	}
	
	
	//--------------------COMMENT METHODS-------------------//

	@SuppressWarnings("rawtypes")
	@RequestMapping("get-comments")
	public @ResponseBody List getCommentsByIssueId(@RequestParam("issueId") int id,
			CommentServiceImpl service) {
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
		System.out.println("email: " + comment.get("email") + "issue id: " + comment.get("issueId"));
		return comment;
	}
	
	
	
	//--------------------USER METHODS-------------------//
	
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
		}
		catch (Exception ex) {
		}
		
		return message;
	}
	
	
	
	@RequestMapping(value = "edit-user/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editUser(@ModelAttribute("user") UserModel user,
			UserServiceImpl service) {
		String message = null;
		
		try {
		} 
		catch (Exception ex) {
		}
		
		return message;
	}
	
	
	
	@RequestMapping(value = "remove-user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String removeUser(@PathVariable("id") int id,
			UserServiceImpl service) {
		String message = null;
		
		try {
			service.deleteUser(service.getUserByID(id));
		} 
		catch (Exception ex) {
		}
		
		return message;
	}
}
