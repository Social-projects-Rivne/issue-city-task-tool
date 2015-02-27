package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.impl.UserServiceImpl;

public class GetUserByIdImplTest {

	private UserModel user;

	/*
	 * create and add user in table whose we will get
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("===== start add new user =====");
		this.user = new UserModel("Name", "Email@mal.er", "login1", 1,
				"asdasdasd", "avatar");

		new UserServiceImpl().addUser(user);
		System.out.println("=====  new user added =====");
	}

	// and delete our test-user from table after test
	@After
	public void tearDown() throws Exception {
		new UserServiceImpl().deleteUser(user);
		System.out.println("=====  new user deleted =====");

	}

	@Test
	public void testGetUserByID() {
		try {
			System.out.println("=====  stert to get user =====");
			UserModel user2 = new UserServiceImpl().getUserByID(user.getId());
			System.out.println("===== user getted =====" + user2.getEmail());

			assertTrue("sucsess", true);

		} catch (Exception ex) {
			assertTrue(ex.toString(), false);
		}
	}

}
