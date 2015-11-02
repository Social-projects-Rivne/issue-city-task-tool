package edu.com.softserveinc.bawl.controllers;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.dto.IssueDto;
import edu.com.softserveinc.bawl.dto.ResponseDTO;
import edu.com.softserveinc.bawl.dto.UserHistoryDto;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class IssueController {

	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(IssueController.class);
    public static final String ISSUE_ADDED = "Issue was successfully added";
    public static final String ISSUE_NOT_ADDED = "Some problem occured! Issue was not added";

    @Autowired
	private IssueService issueService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	@Autowired
	HistoryService historyService;

	@PostAuthorize("hasRole('ROLE_MANAGER') or {2,5}.contains(returnObject.getStatusId())")
	@RequestMapping("issue/{id}")
	public @ResponseBody IssueDto getIssue(@PathVariable("id") int issueId) {
		return DTOAssembler.getIssueDto(historyService.getLastIssueByIssueID(issueId));
	}

	/**
	 * Returns list of UserHistoryDto by issue id
	 * @param id issue id
	 * @return list of UserHistoryDto
	 */
	@RequestMapping(value = "issue/{id}/history", method = RequestMethod.GET)
	public @ResponseBody List<UserHistoryDto> getUserHistoryAction(@PathVariable int id ) {
		IssueModel issue = issueService.getById(id);
		return DTOAssembler.getUserHistoryDtos(issue, userService);
	}


	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "issue", method = RequestMethod.GET)
	public @ResponseBody List<IssueDto> getAllIssues(){
		return DTOAssembler.getAllIssuesDto(issueService.loadIssuesList());
	}

	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "delete-issue/{id}", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> deleteIssue(@PathVariable("id") int issueId, Map<String, String> message) {
		int userId = getCurrentUserId();
		if (userId != 0){
			issueService.deleteProblem(issueId, userId);
			try {
				mailService.notifyForIssue(issueId, "Issue has been deleted.");
			} catch (RequestFailedException e) {
				message.put("message", "Some problem with sending email. Try again and check your email");
			}
		}
		return message;
	}

    /**
     * Returns all issues with statuses 2=approved, 5=toresolve
     * @return list of all issues
     */
	@PostFilter("hasRole('ROLE_MANAGER') or {2,5}.contains(filterObject.getStatusId())")
	@RequestMapping("get-issues")
	public @ResponseBody List<IssueDto> getIssues() {
		return DTOAssembler.getAllIssuesDto(historyService.getLastUniqueIssues());
	}

    /**
     * Adds new issue
     * @param request
     * @return
     */
	@RequestMapping(value = "issue", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO addIssue(@RequestBody IssueDto request) {
		ResponseDTO responseDTO = new ResponseDTO();
		int userId = getCurrentUserId();
		if (userId != 0) {
			CategoryModel category = categoryService.getCategoryByNameOrAddNew(request.getCategory().toLowerCase());
			IssueModel issue = new IssueModel(request.getName(),
					request.getDescription(), request.getMapPointer(),
                    request.getAttachments(), category,
                    request.getPriorityId(), IssueStatus.get(request.getStatus()));
			try {
				issueService.addProblem(issue, userId);
				responseDTO.setMessage(ISSUE_ADDED);
			} catch (Exception ex) {
				responseDTO.setMessage(ISSUE_NOT_ADDED);
			}
		}

		return responseDTO;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "issue/{id}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, String>  editIssue(@RequestBody Map request, Map<String, String> message) {
		
		if(request.size() == 10) {
			int userId = getCurrentUserId();
			if (userId != 0) {
				final Object categoryName = request.get("category");
				if (categoryName != null) {
					CategoryModel category = categoryService.getCategoryByName(categoryName.toString().toLowerCase());
					int issueId = Integer.parseInt(request.get("id").toString());
					List<IssueModel> issueModel = issueService.loadIssuesList();
					for (IssueModel issueModelWithID : issueModel) {
						if (issueId == issueModelWithID.getId()) {
							IssueModel issue = issueModelWithID;
							issue.setCategory(category);
							issueService.editProblem(issue, userId);
							try {
								mailService.notifyForIssue(issueId, "Issue has been updated.");
							} catch (RequestFailedException e) {
								message.put("message", "Some problem with sending email. Try again and check your email");
							}
						}
					}
				}

				if (request.get("status") != "") {
					String status = request.get("status").toString().toLowerCase();
					int statusId = 0;
					int issueId = Integer.parseInt(request.get("id").toString());
					List<IssueModel> issueModel = issueService.loadIssuesList();
					for (IssueModel issueModelWithID : issueModel) {
						if (issueId == issueModelWithID.getId()) {
							IssueModel issue = issueModelWithID;

							if (!request.get("attachments").toString().equals(""))
								issue.setAttachments(request.get("attachments").toString());
							if (!request.get("description").toString().equals(""))
								issue.setDescription(request.get("description").toString());

							issue.setStatus(IssueStatus.get(status));
							issueService.editProblem(issue, userId);
							try {
								mailService.notifyForIssue(issueId, "Issue has been updated.");
							} catch (RequestFailedException e) {
								message.put("message", "Some problem with sending email. Try again and check your email");
							}
						}
					}
				}
			}
		}
		return message;
	}
	
	

	// method for change status issue on to resolve
	@RequestMapping(value = "to-resolve/{id}", method = RequestMethod.POST)
	public @ResponseBody Map<String, String>  toResolve(@PathVariable("id") int id, Map<String, String> message) {
		int userId = getCurrentUserId();
		if (userId != 0) {
			IssueModel issue = historyService.getLastIssueByIssueID(id);
			issue.setStatus(IssueStatus.RESOLVED);
			issueService.editProblem(issue, userId);
			try {
				mailService.notifyForIssue(id, "Issue has been marked as possibly resolved.");
			} catch (RequestFailedException e) {
				message.put("message", "Some problem with sending email. Try again and check your email");
			}
		}
		return message;
	}


	private int getCurrentUserId(){
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (currentUserLoginName.equals("anonymousUser")) {
			return 0;
		}
		else {
			return userService.getByLogin(currentUserLoginName).getId();
		}
	}

}
