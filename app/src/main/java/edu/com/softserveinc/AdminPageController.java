package edu.com.softserveinc;


import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import edu.com.softserveinc.main.models.AdminModel;
import edu.com.softserveinc.main.models.UserModel;

/**
 * Handles requests for the application addUser page.
 */
@Controller
public class AdminPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "addUser", method = RequestMethod.GET)
	public String addUser(Locale locale, Model model) {
		
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		UserModel user = new UserModel("Nazar","NazarMail@ukr.net","login",1,"1234567890","user_avatar.jpg");

		// testing jsp
		model.addAttribute("userName", user.getName());
		model.addAttribute("userMail", "<b>"+user.getEmail()+"</b>");
		model.addAttribute("userLogin", user.getLogin());
			
			// on every loading it adds a new user in DB 
			// try connect to db and create the SessionFactory from hibernate.cfg.xml
		
		try {
		
			new AdminModel().addUser(user);
			
			}
		//TODO: Create annotation for those exception!  
		catch(org.hibernate.exception.ConstraintViolationException ex){	
			
			//TODO:add here notification window
			//TODO:chage it on logger
			System.out.println("==================== !!! USER EXISTS !!! =================");
			System.out.println(ex);
			
		}
	        
		catch (Throwable ex) {
	        	
	          System.out.println("Initial SessionFactory creation failed." + ex); // TODO: change it on logger
	          
	        }
		
		return "addUser";
	}
	
	@RequestMapping(value="addUser.save", method=RequestMethod.POST)
    public String checkPersonInfo(@Valid UserModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/";
    }
	
}
