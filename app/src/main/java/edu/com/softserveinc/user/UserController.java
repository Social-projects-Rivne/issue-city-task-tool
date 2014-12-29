package edu.com.softserveinc.user;
import java.util.HashMap;
import java.util.Map;
 
import javax.validation.Valid;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.com.softserveinc.user.UserModel;

@Controller
public class UserController{
	
	 private Map<String, UserModel> users = null;
     
	    public UserController(){
	    	users = new HashMap<String, UserModel>();
	    }
	    
	    @RequestMapping(value = "/user/save", method = RequestMethod.GET)
	    public String saveUserPage(Model model) {
	       
	        model.addAttribute("user", new UserModel());
	        return "userSave";
	    }
	    
	    @RequestMapping(value = "/user/save.do", method = RequestMethod.POST)
	    public String saveUserAction(
	            @Valid UserModel user,
	            BindingResult bindingResult, Model model) {
	        if (bindingResult.hasErrors()) {
	            return "userSave";
	        }
	        model.addAttribute("user", user);
	        users.put(user.getEmail(), user);
	        return "userSave";
	    }
}
