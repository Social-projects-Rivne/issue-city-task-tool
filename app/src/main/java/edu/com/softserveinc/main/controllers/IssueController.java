package edu.com.softserveinc.main.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.CategoryModel;
import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.services.CategoryService;
import edu.com.softserveinc.main.services.IssueService;

@Controller
public class IssueController {
	
	@Autowired
	private IssueService service;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping("issue/{id}")
	public @ResponseBody IssueModel getIssue(@PathVariable("id") int id) {
		return service.getByID(id);
	}
	
	@RequestMapping(value = "delete-issue/{id}", method = RequestMethod.POST)
	public @ResponseBody void deleteIssue(@PathVariable("id") int id) {
		service.deleteProblem(id);
		System.out.print(id);
	}
		
	
	@RequestMapping("get-issues")
	public @ResponseBody List<IssueModel> getIssues() {

		return service.loadIsssueList();
	}
		

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue", method = RequestMethod.POST)
	public @ResponseBody String addIssue(@RequestBody Map request) {
		
		String message = null;
		String category = request.get("category").toString().toLowerCase();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		CategoryModel categoryModel = null;
		int categoryId = 0;
		
		for(int i = 0; i < categories.size(); i++) {
			categoryModel = categories.get(i);
			if(category.equals(categoryModel.getName())) {
				categoryId = categoryModel.getId();
				break;
			}
		}
		
		if(categoryId == 0) {
			categoryService.addCategory(new CategoryModel(category));
			categoryId = categoryService.getCategoryByName(category).getId();
		}
		
		IssueModel issue = new IssueModel(
				request.get("name").toString(),
				request.get("description").toString(),
				request.get("mapPointer").toString(),
				request.get("attachments").toString(),
				categoryId,
				Integer.parseInt(request.get("priorityId").toString()),
				Integer.parseInt(request.get("statusId").toString())
		);

		service.addProblem(issue);		
		
		return message;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editIssue(@RequestBody Map request) {
		
		String message = null;
		String category = request.get("category").toString().toLowerCase();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		CategoryModel categoryModel = null;
		int categoryId = 0;
		
		for(int i = 0; i < categories.size(); i++) {
			categoryModel = categories.get(i);
			if(category.equals(categoryModel.getName())) {
				categoryId = categoryModel.getId();
				break;
			}
		}
		
		if(categoryId == 0) {
			categoryService.addCategory(new CategoryModel(category));
			categoryId = categoryService.getCategoryByName(category).getId();
		}
		
		IssueModel issue = service.getByID(Integer.parseInt(request.get("id").toString()));
		issue.setCategoryId(categoryId);

		service.editProblem(issue);		
		
		return message;
	}
	
}
