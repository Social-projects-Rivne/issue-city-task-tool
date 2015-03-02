package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.UserService;

public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	private UserModel user;

	@Before
	public void setUp() throws Exception {

		System.out.println("test start");
		this.user = new UserModel("Name", "Email@mal.er", "login1", 1,
				"asdasdasd", "avatar");
	}

	@Test
	public void testUserService() {
		try {

			System.out.println("add new user");
			// add new user
			userService.addUser(user);

			System.out.println("change user data");
			// change user data
			user.setAvatar("new_avata_url");

			System.out.println("editing user");
			// editing user
			userService.editUser(user);

			assertTrue("sucsess", true);

		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue(ex.toString(), false);
		}
	}
}
