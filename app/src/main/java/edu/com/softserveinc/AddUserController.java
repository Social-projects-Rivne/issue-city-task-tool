package edu.com.softserveinc;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.com.softserveinc.main.dao.users.UserExist;
import edu.com.softserveinc.main.models.AdminModel;
import edu.com.softserveinc.main.models.UserModel;

@Controller
public class AddUserController extends HttpServlet {

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		int role_id = 1;
		String password = request.getParameter("password");
		String avatar = request.getParameter("avatar");
		

		try {
			if(new UserExist(login).isNotExist())
				new AdminModel().addUser(
						new UserModel(name, email, login, role_id, password, avatar));

		} catch (org.hibernate.exception.ConstraintViolationException ex) {

			// TODO:add here notification window
			// TODO:chage it on logger
			System.out
					.println("==================== !!! USER EXISTS !!! =================");
			System.out.println(ex);

		}

		response.sendRedirect("admin-toolpage");
	}
}
