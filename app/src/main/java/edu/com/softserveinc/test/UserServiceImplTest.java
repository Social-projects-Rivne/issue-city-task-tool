package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.dao.users.UserServiceImpl;
import edu.com.softserveinc.main.models.UserModel;

public class UserServiceImplTest {

	@Autowired
	private UserServiceImpl userService;

	private UserModel user;

	@Before
	public void setUp() throws Exception {
		this.user = new UserModel("Name", "Email@mal.er", "login1", 1,
				"asdasdasd", "avatar");
	}

	@Test
	public void testUserService() {
		try {
			// add new user
			userService.addUser(user);
			// change user data
			user.setAvatar("new_avata_url");
			// editing user
			userService.editUser(user);
			// removing
			userService.deleteUser(user);

			assertTrue("sucsess", true);

		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue(ex.toString(), false);
		}
	}
}
