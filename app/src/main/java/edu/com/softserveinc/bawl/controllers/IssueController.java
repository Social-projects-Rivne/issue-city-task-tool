package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.dto.IssueDTO;
import edu.com.softserveinc.bawl.dto.ResponseDTO;
import edu.com.softserveinc.bawl.dto.UserHistoryDTO;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.com.softserveinc.bawl.models.enums.IssueStatusHelper.getIssueStatusForAddIssue;
import static edu.com.softserveinc.bawl.models.enums.IssueStatusHelper.getIssueStatusForResolving;

@RestController
@RequestMapping("issue")
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

    @RequestMapping("{id}")
    @ResponseBody
    public IssueDTO getIssue(@PathVariable("id") int issueId) {
        final IssueModel lastIssueByIssueID = historyService.getLastIssueByIssueID(issueId);
        return DTOAssembler.getIssueDto(lastIssueByIssueID);
    }

    /**
     * Returns list of UserHistoryDto by issue id
     *
     * @param id issue id
     * @return list of UserHistoryDto
     */

    @RequestMapping(value = "{id}/history", method = RequestMethod.GET)
    @ResponseBody
    public List<UserHistoryDTO> getUserHistoryAction(@PathVariable int id) {
        final IssueModel issue = issueService.getById(id);
        return DTOAssembler.getUserHistoryDtos(issue, userService);
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<IssueDTO> getAllIssues() {
        final List<IssueModel> allIssues = issueService.loadIssuesList();
        return DTOAssembler.getAllIssuesDto(allIssues);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteIssue(@PathVariable("id") int issueId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            issueService.deleteProblem(issueId, userService.getCurrentUserId());
            mailService.notifyForIssue(issueId, "Issue has been deleted.");
        } catch (Exception ex) {
            responseDTO.setMessage(FAILURE_DELETE);
        }
        return responseDTO;
    }

    /**
     * Returns all issues with statuses 2=approved, 5=toresolve
     *
     * @return list of all issues
     */

    //TODO add filter or {2,5}.contains(filterObject.getStatusId())
    //@PostFilter("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public List<IssueDTO> getIssues() {
        return DTOAssembler.getAllIssuesDto(historyService.getLastUniqueIssues());
    }

    /**
     * Adds new issue
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addIssue(@RequestBody IssueDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserModel userModel = userService.getCurrentUser();
            CategoryModel category = categoryService.getCategoryByNameOrAddNew(request.getCategory());
            IssueModel issue = new IssueModel(request.getName(),
                    request.getDescription(), request.getMapPointer(),
                    request.getAttachments(), category,
                    request.getPriorityId(), IssueStatus.valueOf(request.getStatus()));
            issue.setStatus(getIssueStatusForAddIssue(userModel.getRole()));
            issueService.addProblem(issue, userModel.getId());
            responseDTO.setMessage(ISSUE_ADDED);
        } catch (Exception ex) {
            responseDTO.setMessage(ISSUE_NOT_ADDED);
        }
        return responseDTO;

    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDTO editIssue(@RequestBody IssueDTO issueDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            int userId = userService.getCurrentUserId();
            int issueId = issueDTO.getId();
            IssueModel editedIssue = issueService.getById(issueId);

            final String categoryName = issueDTO.getCategory();
            if (!StringUtils.isEmpty(categoryName)) {
                CategoryModel category = categoryService.getCategoryByNameOrAddNew(categoryName);
                editedIssue.setCategory(category);
            }

            final String status = issueDTO.getStatus();
            if (!StringUtils.isEmpty(status)) {
                editedIssue.setStatus(IssueStatus.valueOf(status.toUpperCase()));
            }

            final String attachments = issueDTO.getAttachments();
            if (!StringUtils.isEmpty(attachments)) {
                editedIssue.setAttachments(attachments);
            }

            final String description = issueDTO.getDescription();
            if (!StringUtils.isEmpty(description)) {
                editedIssue.setDescription(description);
            }

            final String name = issueDTO.getName();
            if (!StringUtils.isEmpty(name)) {
                editedIssue.setName(name);
            }

            mailService.notifyForIssue(issueId, "Issue has been updated.");
            issueService.editProblem(editedIssue, userId);
            responseDTO.setMessage(SUCCESS_UPDATE);
        } catch (Exception ex) {
            responseDTO.setMessage(NOT_AUTHORIZED);
        }
        return responseDTO;
    }


    @RequestMapping(value = "resolve/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO toResolve(@PathVariable("id") int id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserModel userModel = userService.getCurrentUser();
            IssueModel issue = historyService.getLastIssueByIssueID(id);
            issue.setStatus(getIssueStatusForResolving(userModel.getRole()));
            issueService.editProblem(issue, userModel.getId());
            responseDTO.setMessage(SUCCESS_MARKED);
        } catch (Exception ex){
            responseDTO.setMessage(NOT_AUTHORIZED);
        }
        return responseDTO;
    }



}
