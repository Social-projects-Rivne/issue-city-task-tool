package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.services.IssueServiceImpl;

public class ProblemServiceTest {

	private IssueServiceImpl issuService = new IssueServiceImpl();
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
			issuService.addProblemm(issue);

			issue = issuService.getByID(issue.getId());

			issue.setDescription("add new attachments");

			issuService.editProblemm(issue);

			issuService.deletteProblemm(issue);
			
			assertTrue("sucsess", true);
			
		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue(ex.toString(), false);

		}
	}
}
