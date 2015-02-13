package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.services.IssueServiceImpl;

public class IssueServiceTest {

	IssueModel issue;

	@Before
	public void setUp() throws Exception {
		issue = new IssueModel(1, "first problem", "bigg problemm!!", "0, 0",
				"smth", 1);
	}

	@Test
	public void testIssueService() {
		boolean result = false;
		try {

			// add
			System.out.println("start aading");
			new IssueServiceImpl().addProblemm(issue);

			// edit
			System.out.println("start editing");
			issue.setDescription("Changed description");
			new IssueServiceImpl().editProblemm(issue);

			// get by id
			if (issue.equals(new IssueServiceImpl().getByID(issue.getId()))) {
				result = true;
			}

			// delete
			System.out.println("start deleting");
			new IssueServiceImpl().deleteProblemm(issue);

			// return result of test
			if (result)
				assertTrue("sucsess", true);

		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue(ex.toString(), false);
		}
	}
}
