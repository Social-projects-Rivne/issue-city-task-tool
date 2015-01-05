package edu.com.softserveinc;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.com.softserveinc.main.dao.AddUserImpl;
import edu.com.softserveinc.main.dao.DeleteUserImpl;
import edu.com.softserveinc.main.models.UserModel;

@Controller
public class RemoveUserController extends HttpServlet {
	
	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("remove-hidden"));
		
		DeleteUserImpl del = new DeleteUserImpl();
		del.deleteUser(id);
		
		response.sendRedirect("http://localhost:8080/softserveinc/admin-toolpage");
	}
	
}
