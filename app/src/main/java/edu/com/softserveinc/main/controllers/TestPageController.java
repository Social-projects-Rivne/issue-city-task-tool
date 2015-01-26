package edu.com.softserveinc.main.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.CommentServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/cont")
public class TestPageController {

	/* @RequestMapping(value = "/testPage", method = RequestMethod.GET)
	public String testPage() {
		return "testPage";
	}
	
	@RequestMapping(value = "add-comment", method = RequestMethod.POST)
	public String addComment(HttpServletRequest request, CommentServiceImpl commentService) {
		
		return "testPage";
	}
	*/
	
	@RequestMapping(method = RequestMethod.GET)
	  public @ResponseBody UserModel get(HttpServletResponse res) {
		 UserModel user = new UserModel("Name", "Email@mal.er", "login1", 1,
					"asdasdasd", "avatar");
	     return user;
	  }
	 
	  @RequestMapping(value="person", method = RequestMethod.POST)
	  public @ResponseBody UserModel post( @RequestBody final  UserModel user) {    
	 
	      System.out.println(user.getId() + " " + user.getName());
	      return user;
	  }
}
