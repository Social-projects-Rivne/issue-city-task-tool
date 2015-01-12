package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.com.softserveinc.main.models.AdminService;
import edu.com.softserveinc.main.models.UserModel;

public class AdminServiceTest {

	UserModel user;

	@Before
	public void setUp() throws Exception {
		user = new UserModel("TestName", "test@mail.com", "testLogin", 1,
				"testPassword", "null");
	}

	@Test
	public void testAddUser() {
		try {
			new AdminService().addUser(user);
			assertTrue("sucsess", true);
		} catch (Exception ex) {
			assertTrue(ex.toString(), false);
		}
	}

	@Test
	public void testEditUser() {
		try {
			user.setAvatar("new Avatar");
			new AdminService().editUser(user);
			assertTrue("sucsess", true);
		} catch (Exception ex) {
			assertTrue(ex.toString(), false);
		}
	}

	@Test
	public void testGetUserByID() {
		try {
			if (user.equals(new AdminService().getUserByID(user.getId())))
				assertTrue("sucsess", true);
		} catch (Exception ex) {
			assertTrue(ex.toString(), false);
		}
	}

	@Test
	public void testDeleteUser() {
		try {
			new AdminService().deleteUser(user);
			assertTrue("sucsess", true);
		} catch (Exception ex) {
			assertTrue(ex.toString(), false);
		}
	}
}
