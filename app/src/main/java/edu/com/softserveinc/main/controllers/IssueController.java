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
import edu.com.softserveinc.main.models.StatusModel;
import edu.com.softserveinc.main.services.CategoryService;
import edu.com.softserveinc.main.services.IssueService;
import edu.com.softserveinc.main.services.StatusService;

@Controller
public class IssueController {
	
	@Autowired
	private IssueService service;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StatusService statusService;
	
	
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
	public @ResponseBody Map<String, String> addIssue(@RequestBody Map request, Map<String, String> message) {
		
		String category = request.get("category").toString().toLowerCase();
		String status = request.get("status").toString().toLowerCase();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		List<StatusModel> statuses = statusService.loadStatusList();
		CategoryModel categoryModel = null;
		StatusModel statusModel = null;
		int categoryId = 0;
		int statusId = 0;
		
		for(int i = 0; i < categories.size(); i++) {
			categoryModel = categories.get(i);
			if(category.equals(categoryModel.getName())) {
				categoryId = categoryModel.getId();
				break;
			}
		}
		
		for(int i = 0; i < statuses.size(); i++) {
			statusModel = statuses.get(i);
			if(status.equals(statusModel.getName())) {
				statusId = statusModel.getId();
				break;
			}
		}
		
		if(categoryId == 0) {
			categoryService.addCategory(new CategoryModel(category));
			categoryId = categoryService.getCategoryByName(category).getId();
		}
		
		if(statusId == 0) {
			statusService.addStatus(new StatusModel(status));
			statusId = statusService.getStatusByName(status).getId();
		}
		
		IssueModel issue = new IssueModel(
				request.get("name").toString(),
				request.get("description").toString(),
				request.get("mapPointer").toString(),
				request.get("attachments").toString(),
				categoryId,
				Integer.parseInt(request.get("priorityId").toString()),
				statusId
		);

		try {
			service.addProblem(issue);
			message.put("message", "Issue was successfully added");
		}
		catch (Exception ex) {
			message.put("message", "Some problem occured! Issue was not added");
		}		
		
		return message;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editIssue(@RequestBody Map request) {
		System.out.println("IssueController java method");
		String message = null;
		String category = request.get("category").toString().toLowerCase();
		String status = request.get("status").toString().toLowerCase();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		List<StatusModel> statuses = statusService.loadStatusList();
		CategoryModel categoryModel = null;
		StatusModel statusModel = null;
		int categoryId = 0;
		int statusId = 0;
		
		IssueModel issue = service.getByID(Integer.parseInt(request.get("id").toString()));
		System.out.println(request.get("description"));
		System.out.println(service.getByID(Integer.parseInt(request.get("id").toString())));
		
		if(!category.equals("")) {
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
			
			issue.setCategoryId(categoryId);
		}
		else {
			for(int i = 0; i < statuses.size(); i++) {
				statusModel = statuses.get(i);
				if(status.equals(statusModel.getName())) {
					statusId = statusModel.getId();
					break;
				}
			}
			
			if(statusId == 0) {
				statusService.addStatus(new StatusModel(status));
				statusId = statusService.getStatusByName(status).getId();
			}
			
			issue.setStatusId(statusId);
		}
		
		service.editProblem(issue);		
		
		return message;
	}
	
}
