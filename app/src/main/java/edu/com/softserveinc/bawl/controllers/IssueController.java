package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.DTOMapper;
import edu.com.softserveinc.bawl.dto.IssueDto;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.StatusModel;
import edu.com.softserveinc.bawl.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IssueController {

	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(IssueController.class);

	@Autowired
	private IssueService issueService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	@Autowired
	HistoryService historyService;

	@PostAuthorize("hasRole('ROLE_MANAGER') or {2,5}.contains(returnObject.getStatusId())")
	@RequestMapping("issue/{id}")
	public @ResponseBody IssueModel getIssue(@PathVariable("id") int issueId) {
		IssueModel issues = historyService.getLastIssueByIssueID(issueId);
		return issues;
	}


	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "issue/", method = RequestMethod.GET)
	public @ResponseBody List<IssueDto> getAllIssues(){

		List<IssueModel> listIssueModel= issueService.loadIssuesList();

		for (IssueModel issueModel : listIssueModel){

			if(issueModel.getStatusId() == 5){
				listIssueModel.remove(issueModel);
			}
		}

		List<IssueDto> listIssueDto = DTOMapper.getAllIssuesDto(listIssueModel);

		return listIssueDto;
	}

	@RequestMapping(value = "delete-issue/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public @ResponseBody void deleteIssue(@PathVariable("id") int issueId) {

		int userId = getCurrentUserId();
		if (userId != 0){
			issueService.deleteProblem(issueId, userId);
			mailService.notifyForIssue(issueId, "Issue has been deleted.");
		}

	}
 
	@PostFilter("hasRole('ROLE_MANAGER') or {2,5}.contains(filterObject.getStatusId())")//2=approved, 5=toresolve
	@RequestMapping("get-issues")
	public @ResponseBody List<IssueModel> getIssues() {
		List<IssueModel> issues = historyService.getLastUniqueIssues();
		return issues;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addIssue(@RequestBody Map request,
			Map<String, String> message) {
		int userId = getCurrentUserId();
		if (userId != 0) {

			String category = request.get("category").toString().toLowerCase();
			String status = request.get("status").toString().toLowerCase();
			List<CategoryModel> categories = categoryService.loadCategoriesList();
			List<StatusModel> statuses = statusService.loadStatusList();
			CategoryModel categoryModel = null;
			StatusModel statusModel = null;
			int categoryId = 0;
			int statusId = 0;
			//int categoryId = categories.stream().filter(c -> c.getName() == category).findFirst().get().getId();
			//int statusId = categories.stream().filter(c -> c.getName() == category).findFirst().get().getId();

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
				issueService.addProblem(issue, userId);
				message.put("message", "Issue was successfully added");
			} catch (Exception ex) {
				message.put("message", "Some problem occured! Issue was not added");
			}
		}

		return message;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue/{id}", method = RequestMethod.PUT)
	public @ResponseBody String editIssue(@RequestBody Map request) {
		
		String message = null;
		
		if(request.size() == 10) {
			int userId = getCurrentUserId();
			if (userId != 0) {
				if (request.get("category") != "") {
					String category = request.get("category").toString().toLowerCase();
					List<CategoryModel> categories = categoryService.loadCategoriesList();
					CategoryModel categoryModel = null;
					int categoryId = 0;
					int issueId = Integer.parseInt(request.get("id").toString());
					List<IssueModel> issueModel = issueService.loadIssuesList();
					for (IssueModel issueModelWithID : issueModel) {
						if (issueId == issueModelWithID.getId()) {
							IssueModel issue = issueModelWithID;

							for (int i = 0; i < categories.size(); i++) {
								categoryModel = categories.get(i);
								if (category.equals(categoryModel.getName())) {
									categoryId = categoryModel.getId();
									break;
								}
							}

							if (categoryId == 0) {
								categoryService.addCategory(new CategoryModel(category));
								categoryId = categoryService.getCategoryByName(category).getId();
							}

							issue.setCategoryId(categoryId);
							issueService.editProblem(issue, userId);
							mailService.notifyForIssue(issueId, "Issue has been updated.");
						}
					}
				}

				if (request.get("status") != "") {
					String status = request.get("status").toString().toLowerCase();
					List<StatusModel> statuses = statusService.loadStatusList();
					StatusModel statusModel = null;
					int statusId = 0;
					int issueId = Integer.parseInt(request.get("id").toString());
					List<IssueModel> issueModel = issueService.loadIssuesList();
					for (IssueModel issueModelWithID : issueModel) {
						if (issueId == issueModelWithID.getId()) {
							IssueModel issue = issueModelWithID;

							for (int i = 0; i < statuses.size(); i++) {
								statusModel = statuses.get(i);
								if (status.equals(statusModel.getName())) {
									statusId = statusModel.getId();
									break;
								}
							}

							if (!request.get("attachments").toString().equals(""))
								issue.setAttachments(request.get("attachments").toString());
							if (!request.get("description").toString().equals(""))
								issue.setDescription(request.get("description").toString());

							issue.setStatusId(statusId);
							issueService.editProblem(issue, userId);
							mailService.notifyForIssue(issueId, "Issue has been updated.");
						}
					}
				}
			}
		}
		return message;
	}
	
	

	// method for change status issue on to resolve
	@RequestMapping(value = "to-resolve/{id}", method = RequestMethod.POST)
	public @ResponseBody void toResolve(@PathVariable("id") int id) {
		int userId = getCurrentUserId();
		if (userId != 0) {
			IssueModel issue = historyService.getLastIssueByIssueID(id);
			issue.setStatusId(5);
			issueService.editProblem(issue, userId);
			mailService.notifyForIssue(id, "Issue has been marked as possibly resolved.");
		}
	}

	public int getCurrentUserId(){
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (currentUserLoginName.equals("anonymousUser")) {
			return 0;
		}
		else {
			return userService.getByLogin(currentUserLoginName).getId();
		}
	}

}
