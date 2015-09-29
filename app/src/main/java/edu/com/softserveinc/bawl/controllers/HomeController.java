package edu.com.softserveinc.bawl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;

@Controller
public class HomeController {
	
	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

}
