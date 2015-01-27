package edu.com.softserveinc.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.CommentServiceImpl;
import edu.com.softserveinc.main.services.UserServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/cont")
public class TestPageController {

	@RequestMapping(value = "/testPage", method = RequestMethod.GET)
	public String testPage() {
		new CommentServiceImpl().addComment(new CommentModel("asdads", "asa", "ss@ss.s", 1));
		return "testPage";
	}

	@RequestMapping(value = "add-comment", method = RequestMethod.POST)
	public String addComment(HttpServletRequest request,
			CommentServiceImpl commentService) {

		return "testPage";
	}

	@RequestMapping(value = "person", method = RequestMethod.POST)
	public @ResponseBody UserModel post(@RequestBody final UserModel user) {

		System.out.println(user.getId() + " " + user.getName());
		return user;
	}

	@RequestMapping(value = "testPage", method = RequestMethod.POST)
	public @ResponseBody UserModel createUser(@RequestBody final UserModel user) {
		new UserServiceImpl().addUser(user);
		System.out.println("user: " + user.getName());
		return user;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public java.util.List getById(@PathVariable int id) {

		return new UserServiceImpl().loadUsersList();
	}
}
