package edu.com.softserveinc.main.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.CategoryModel;
import edu.com.softserveinc.main.services.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService service;
		
	@RequestMapping("get-categories")
	public @ResponseBody List<CategoryModel> getCategories() {
		return service.loadCategoriesList();
	}
	
	@RequestMapping(value = "category", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addCategory(@RequestBody CategoryModel category,
			Map<String, String> message) {
		
		try {
			service.addCategory(category);
			message.put("message", "New category successfully added");
		}
		catch(Exception e) {
			message.put("message", "Some problem occured! New category was not added");
		}
		
		return message;
	}

	
}
