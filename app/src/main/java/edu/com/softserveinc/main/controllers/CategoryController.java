package edu.com.softserveinc.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
	
}
