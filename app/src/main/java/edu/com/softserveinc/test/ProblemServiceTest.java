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
		issue = new IssueModel("first problem", "bigg problemm!!", "0, 0",
				"smth", 1);
	}
	//TODO: add comments!!!
	@Test
	public void testAddProblemm() {
		try {
			System.out.println("start");
			issuService.addProblemm(issue);

			issue = issuService.getByID(issue.getId());

			issue.setAttachments("add new attachments");

			issuService.editProblemm(issue);

			issuService.deleteProblemm(issue);
			
			assertTrue("sucsess", true);
			
		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue(ex.toString(), false);

		}
	}
}
