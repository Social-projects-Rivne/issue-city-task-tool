package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.services.IssueService;

public class ProblemServiceTest {

	@Autowired
	private IssueService issueService;
	IssueModel issue = null;

	@Before
	public void setUp() throws Exception {
		issue = new IssueModel("PROBLEM WITH INSERTD ATTACHMENT", "bigg problemm!!", "LatLng(50.63542, 26.30058)",
				"https://pp.vk.me/c6045/v6045569/de2e/vLPjT4Nj8eo.jpg", 1, 1, 1);
	}
	//TODO: add comments!!!
	@Test
	public void testAddProblemm() {
		try {
			System.out.println("start");
			issueService.addProblem(issue);

			issue = issueService.getByID(issue.getId());

			issue.setDescription("add new attachments");

			issueService.editProblem(issue);

			issueService.deleteProblem(issue);
			
			assertTrue("sucsess", true);
			
		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue(ex.toString(), false);

		}
	}
}
