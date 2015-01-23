package edu.com.softserveinc.main.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "add-issue", method = RequestMethod.POST)
	public String addIssue(HttpServletRequest request, IssueServiceImpl service) {
		String issueName = request.getParameter("issueName");
		String issueCategory = request.getParameter("issueCategory");
		String issueDescription = request.getParameter("issueDescription");
		String issueResolution = request.getParameter("issueResolution");
		String issueAttachments = request.getParameter("issueAttachments");
		System.out.println(issueName);
		System.out.println(issueCategory);
		System.out.println(issueDescription);
		System.out.println(issueResolution);
		System.out.println(issueAttachments);
		/*if (new IssueValidator(issue).isValid()) {
			try {
				service.addProblemm(issue);
			} catch (Exception ex) {
				System.out.println("ERROR" + ex.toString());
			}
		}*/
		//TODO: add here notification method! 
		return "home";
	}
	
	@RequestMapping(value = "get-issue", method = RequestMethod.POST)
	public @ResponseBody IssueModel getIssue(@RequestBody Map<String, Object> request,
			IssueServiceImpl issueService) {
		
		System.out.println(request.get("id"));
		
		IssueModel issue = new IssueModel();
		issue.setId(1);
		
		return issue;
	}
}
