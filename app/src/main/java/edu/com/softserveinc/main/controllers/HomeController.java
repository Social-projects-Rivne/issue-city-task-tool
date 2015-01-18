package edu.com.softserveinc.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.services.IssueServiceImpl;
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

	@RequestMapping(value = "add-issue", method = RequestMethod.GET)
	public String addIssue(@ModelAttribute("issue") IssueModel issue,
			IssueServiceImpl service) {
		if (new IssueValidator(issue).isValid()) {
			try {
				service.addProblemm(issue);
			} catch (Exception ex) {
				System.out.println("ERROR" + ex.toString());
			}
		}
		//TODO: add here notification method! 
		return "home";
	}

}
