package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.dao.problems.ProblemServiceImpl;
import edu.com.softserveinc.main.models.ProblemModel;

public class ProblemServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddProblemm() {
		try {
			System.out.println("start");
			new ProblemServiceImpl().addProblemm(new ProblemModel(
					"first problem", "bigg problemm!!", "0, 0", "smth", 1));
			
			assertTrue("sucsess", true);
			
		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue(ex.toString(), false);
			
		}
	}
	
	// it will be later
/*
	@Test
	public void testEditProblemm() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletteProblemm() {
		fail("Not yet implemented");
	}
*/
}
