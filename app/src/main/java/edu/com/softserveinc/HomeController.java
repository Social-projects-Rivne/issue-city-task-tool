package edu.com.softserveinc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.com.softserveinc.main.dao.problems.ProblemServiceImpl;
import edu.com.softserveinc.main.models.ProblemModel;

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
	public String addIssue(@ModelAttribute("issue") ProblemModel issue, ProblemServiceImpl service) {
		service.addProblemm(issue);
		return "home";
	}
	
}
