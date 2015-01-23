package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.services.CommentServiceImpl;

public class CommentServiceImplTest {

	CommentModel comment;
	CommentServiceImpl commentService;
	
	@Before
	public void setUp() throws Exception {
		comment = new CommentModel("new comment", "Nazar", "test@mail.d",1);
		commentService = new CommentServiceImpl();
	}

	@Test
	public void testAddComment() {
		try{
		commentService.addComment(comment);
		assertTrue(true);
		System.out.println("Test done");
		}
		catch(Exception ex){

			System.out.println("Test exception");
			assertFalse(true);
		}
	}
/*
	@Test
	public void testDeleteComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCommentsByIssueId() {
		fail("Not yet implemented");
	}
*/
}
