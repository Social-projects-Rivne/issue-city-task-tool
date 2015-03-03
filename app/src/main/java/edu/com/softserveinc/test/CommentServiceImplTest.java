package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.services.impl.CommentServiceImpl;

public class CommentServiceImplTest {

	CommentModel comment;
	CommentServiceImpl commentService;

	@Before
	public void setUp() throws Exception {
		comment = new CommentModel("asdads", "asa", "ss@ss.s", 1);
		commentService = new CommentServiceImpl();
	}

	@Test
	public void testAddComment() {
		try {
			System.out.println("Test start");
			commentService.addComment(comment);
			assertTrue(true);
			System.out.println("Test done");
		} catch (Exception ex) {

			System.out.println("Test exception");
			assertFalse(true);
		}
	}
	/*
	 * @Test public void testDeleteComment() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testEditComment() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testGetCommentsByIssueId() {
	 * fail("Not yet implemented"); }
	 */
}
