package edu.com.softserveinc.main.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
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
import edu.com.softserveinc.main.services.MailService;
import edu.com.softserveinc.main.services.StatusService;

@Controller
public class IssueController {

	@Autowired
	private IssueService service;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping("issue/{id}")
	public @ResponseBody IssueModel getIssue(@PathVariable("id") int id) {
		return service.getByID(id);
	}

	@RequestMapping(value = "delete-issue/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public @ResponseBody void deleteIssue(@PathVariable("id") int id) {
		service.deleteProblem(id);
		mailService.notifyForIssue(id, "Issue has been deleted.");
		System.out.print(id);
	}

	@PostFilter("hasRole('ROLE_MANAGER') or filterObject.getStatusId() == 2 or filterObject.getStatusId() == 5") //2=approved, 5=toresolve
	@RequestMapping("get-issues")
	public @ResponseBody List<IssueModel> getIssues() {

		return service.loadIsssueList();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addIssue(@RequestBody Map request,
			Map<String, String> message) {

		String category = request.get("category").toString().toLowerCase();
		String status = request.get("status").toString().toLowerCase();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		List<StatusModel> statuses = statusService.loadStatusList();
		CategoryModel categoryModel = null;
		StatusModel statusModel = null;
		int categoryId = 0;
		int statusId = 0;

		for (int i = 0; i < categories.size(); i++) {
			categoryModel = categories.get(i);
			if (category.equals(categoryModel.getName())) {
				categoryId = categoryModel.getId();
				break;
			}
		}

		for (int i = 0; i < statuses.size(); i++) {
			statusModel = statuses.get(i);
			if (status.equals(statusModel.getName())) {
				statusId = statusModel.getId();
				break;
			}
		}

		if (categoryId == 0) {
			categoryService.addCategory(new CategoryModel(category));
			categoryId = categoryService.getCategoryByName(category).getId();
		}

		if (statusId == 0) {
			statusService.addStatus(new StatusModel(status));
			statusId = statusService.getStatusByName(status).getId();
		}

		IssueModel issue = new IssueModel(request.get("name").toString(),
				request.get("description").toString(), request
						.get("mapPointer").toString(), request.get(
						"attachments").toString(), categoryId,
				Integer.parseInt(request.get("priorityId").toString()),
				statusId);

		try {
			service.addProblem(issue);
			message.put("message", "Issue was successfully added");
		} catch (Exception ex) {
			message.put("message", "Some problem occured! Issue was not added");
		}

		return message;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editIssue(@RequestBody Map request) {
		
		String message = null;
		String category = request.get("category").toString().toLowerCase();
		String status = request.get("status").toString().toLowerCase();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		List<StatusModel> statuses = statusService.loadStatusList();
		CategoryModel categoryModel = null;
		StatusModel statusModel = null;
		int categoryId = 0;
		int statusId = 0;
		
		int issueId = Integer.parseInt(request.get("id").toString());
		IssueModel issue = service.getByID(issueId);
		
		issue.setDescription(request.get("description").toString());
	    issue.setAttachments(request.get("attachments").toString());
	    
	    issue.setPriorityId(Integer.parseInt((request.get("priorityId")
				.toString())));
		
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
		mailService.notifyForIssue(issueId, "Issue has been updated.");
		
		return message;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issueEdit/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public @ResponseBody String editIssue2(@RequestBody Map request) {

		System.out.println("IssueController java method");
		String message = null;

		int issueId = Integer.parseInt(request.get("id").toString());
		IssueModel issue = service.getByID(issueId);
		System.out.println(request.get("description"));
		System.out.println(service.getByID(issueId));

		// get from front-end and set to model java
		issue.setDescription(request.get("description").toString());
	    issue.setAttachments(request.get("attachments").toString());
		issue.setCategoryId(Integer.parseInt((request.get("categoryId")
				.toString())));
		issue.setStatusId(Integer.parseInt((request.get("statusId").toString())));
		issue.setPriorityId(Integer.parseInt((request.get("priorityId")
				.toString())));
		// update DB into back-end
		service.editProblem(issue);
		mailService.notifyForIssue(issueId, "Issue has been updated.");
		// issue.setStatusId(statusId);
		return message;
	}

	// method for change status issue on to resolve
	@RequestMapping(value = "to-resolve/{id}", method = RequestMethod.POST)
	public @ResponseBody void toResolve(@PathVariable("id") int id) {
		IssueModel issue = service.getByID(id);
		issue.setStatusId(5);
		service.editProblem(issue);
		mailService.notifyForIssue(id, "Issue has been marked as possibly resolved.");
	}

}
