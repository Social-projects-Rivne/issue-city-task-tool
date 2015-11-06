package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.dto.IssueDTO;
import edu.com.softserveinc.bawl.dto.ResponseDTO;
import edu.com.softserveinc.bawl.dto.UserHistoryDTO;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class IssueController {

	public static final Logger LOG = Logger.getLogger(IssueController.class);

    public static final String ISSUE_ADDED = "Issue was successfully added";
    public static final String ISSUE_NOT_ADDED = "Some problem occured! Issue was not added";
    public static final String NOT_AUTHORIZED = "You are not authorized for this action";
	public static final String SUCCESS_SEND = "Issue has been deleted.";
	public static final String FAILURE_SEND = "Some problem with sending email. Try again and check your email";
	public static final String SUCCESS_DELETE = "Issue has been deleted.";
	public static final String FAILURE_DELETE = "";

	public static final String SUCCESS_UPDATE = "Issue has been updated.";
	public static final String FAILURE_UPDATE = "Some problem with sending email. Try again and check your email";
	public static final String SUCCESS_MARKED = "Issue has been marked as possibly resolved.";
	public static final String FAILURE_MARKED = "Some problem with sending email. Try again and check your email";

    @Autowired
	private IssueService issueService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	@Autowired
	private HistoryService historyService;

	//TODO do filter or {"",5}.contains(returnObject.getStatusId())
	@PostAuthorize("hasRole('ROLE_MANAGER') ")
	@RequestMapping("issue/{id}")
	public @ResponseBody
	IssueDTO getIssue(@PathVariable("id") int issueId) {
		return DTOAssembler.getIssueDto(historyService.getLastIssueByIssueID(issueId));
	}

	/**
	 * Returns list of UserHistoryDto by issue id
	 * @param id issue id
	 * @return list of UserHistoryDto
	 */

	@RequestMapping(value = "issue/{id}/history", method = RequestMethod.GET)
	public @ResponseBody List<UserHistoryDTO> getUserHistoryAction(@PathVariable int id ) {
		IssueModel issue = issueService.getById(id);
		return DTOAssembler.getUserHistoryDtos(issue, userService);
	}


	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "issue", method = RequestMethod.GET)
	public @ResponseBody List<IssueDTO> getAllIssues(){
		return DTOAssembler.getAllIssuesDto(issueService.loadIssuesList());
	}

	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "delete-issue/{id}", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO deleteIssue(@PathVariable("id") int issueId) {
		ResponseDTO responseDTO = new ResponseDTO();
		int userId = getCurrentUserId();
		if (userId != 0){
			try {
				issueService.deleteProblem(issueId, userId);
				mailService.notifyForIssue(issueId, "Issue has been deleted.");
			} catch (Exception ex) {
				responseDTO.setMessage(FAILURE_DELETE);
			}
		}
		responseDTO.setMessage(SUCCESS_DELETE);
		return responseDTO;
	}

    /**
     * Returns all issues with statuses 2=approved, 5=toresolve
     * @return list of all issues
     */

	//TODO add filter or {2,5}.contains(filterObject.getStatusId())
    //@PostFilter("hasRole('ROLE_MANAGER')")
	@RequestMapping("get-issues")
	public @ResponseBody List<IssueDTO> getIssues() {
		return DTOAssembler.getAllIssuesDto(historyService.getLastUniqueIssues());
	}

    /**
     * Adds new issue
     * @param request
     * @return
     */
    @RequestMapping(value = "issue", method = RequestMethod.POST)
	public @ResponseBody
	ResponseDTO addIssue(@RequestBody IssueDTO request) {
		ResponseDTO responseDTO = new ResponseDTO();
            CategoryModel category = categoryService.getCategoryByNameOrAddNew(request.getCategory().toLowerCase());
            IssueModel issue = new IssueModel(request.getName(),
                    request.getDescription(), request.getMapPointer(),
                    request.getAttachments(), category,
					request.getPriorityId(), request.getStatus());
            try {
                issueService.addProblem(issue, getCurrentUserId());
                responseDTO.setMessage(ISSUE_ADDED);
            } catch (Exception ex) {
                responseDTO.setMessage(ISSUE_NOT_ADDED);
            }
		return responseDTO;
	}

	@RequestMapping(value = "issue/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseDTO editIssue(@RequestBody Map request) {
		ResponseDTO responseDTO = new ResponseDTO();
		if(request.size() == 9) {
			int userId = getCurrentUserId();
			if (userId != 0) {
				int issueId = Integer.parseInt(request.get("id").toString());
				IssueModel editedIssue = issueService.getById(issueId);

				if (!request.get("category").toString().equals("")) {
					final String categoryName =  request.get("category").toString().toUpperCase();
					CategoryModel category = categoryService.getCategoryByNameOrAddNew(categoryName);
					editedIssue.setCategory(category);
				}

				if (!request.get("status").toString().equals("")) {
					String status = request.get("status").toString().toUpperCase();
					editedIssue.setStatus(status);
				}
				if (!request.get("attachments").toString().equals("")) {
					editedIssue.setAttachments(request.get("attachments").toString());
				}
				if (!request.get("description").toString().equals("")) {
					editedIssue.setDescription(request.get("description").toString());
				}

				mailService.notifyForIssue(issueId, "Issue has been updated.");
				issueService.editProblem(editedIssue, userId);
			}
		}
		return responseDTO;
	}
	

	// method for change status issue on to resolve
	@RequestMapping(value = "to-resolve/{id}", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO toResolve(@PathVariable("id") int id) {
		ResponseDTO responseDTO = new ResponseDTO();
		int userId = getCurrentUserId();
		if (userId != 0) {
			IssueModel issue = historyService.getLastIssueByIssueID(id);
			issue.setStatus(IssueStatus.RESOLVED);
			issueService.editProblem(issue, userId);
			mailService.notifyForIssue(id, "Issue has been marked as possibly resolved.");
		}
		return responseDTO;
	}


	private int getCurrentUserId(){
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (currentUserLoginName.equals("anonymousUser")) {
			return 0;
		} else {
			return userService.getByLogin(currentUserLoginName).getId();
		}
	}

}
