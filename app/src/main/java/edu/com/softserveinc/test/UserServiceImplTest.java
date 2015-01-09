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
	private int id;

	@Before
	public void setUp() throws Exception {
		this.user = new UserModel("Name","Email@mal.er","login1",1,"asdasdasd","avatar");
	}

	@Test
	public void testAddUser() {
		try {
			new AdminModel().addUser(user);
			this.id = user.getId();

			assertTrue("sucsess", true);

		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue("sucsess", false);
		}
	}

	@Test
	public void testEditUser() {
		try {
			user.setId(id);
			user.setAvatar("new_avata_url");
			
			new AdminModel().editUser(user);
			
			assertTrue("sucsess", true);

		} catch (Exception ex) {
			System.out.println(ex);
			assertTrue("sucsess", false);
		}
	}

	@Test
	public void testDeleteUser() {
		try {
			user.setId(id);
			
			new AdminModel().deleteUser(user);
			
			assertTrue("sucsess", true);

		} catch (Exception ex) {

			assertTrue("sucsess", false);
		}
	}
}
