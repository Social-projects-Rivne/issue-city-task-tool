package edu.com.softserveinc.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.com.softserveinc.main.services.CommentServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestPageController {

	@RequestMapping(value = "/testPage", method = RequestMethod.GET)
	public String testPage() {
		return "testPage";
	}
	
	@RequestMapping(value = "add-comment", method = RequestMethod.POST)
	public String addComment(HttpServletRequest request, CommentServiceImpl commentService) {
		
		return "testPage";
	}
}
