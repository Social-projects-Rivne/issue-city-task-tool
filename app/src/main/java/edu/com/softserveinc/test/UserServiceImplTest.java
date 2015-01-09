package edu.com.softserveinc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.com.softserveinc.main.dao.users.UserServiceImpl;
import edu.com.softserveinc.main.models.AdminModel;
import edu.com.softserveinc.main.models.UserModel;

public class UserServiceImplTest {

	@Autowired
	private UserServiceImpl userService;
	
	
	private UserModel user;
	int id;
	
	@Before
	public void setUp() throws Exception {
		this.user = new UserModel("Name", "mail2@m.com", "login2", 1, "password",
			"ava_url");
	}

	@Test
	public void testAddUser() {
		try {
			new AdminModel().addUser(this.user);
			assertTrue("sucsess", true);
			this.id = user.getId();

		} catch (Exception ex) {
			assertTrue("sucsess", false);
		}
	}

	@Test
	public void testDeleteUser() {
		this.user.setId(id);
		this.user.setName("ChangedName");
		try {
			new AdminModel().editUser(this.user);
			assertTrue("sucsess", true);
		}
		catch (Exception ex) {
			assertTrue("sucsess", false);
		}
	}

	@Test
	public void testEditUser() {
		try {
			new AdminModel().deleteUser(this.user);
			assertTrue("sucsess", true);
		} catch (Exception ex) {
			assertTrue("sucsess", false);
		}
	}

}
