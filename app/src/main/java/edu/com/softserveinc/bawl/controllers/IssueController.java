package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.DTOAssembler;
import edu.com.softserveinc.bawl.dto.pojo.IssueDTO;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.UserHistoryDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static edu.com.softserveinc.bawl.dto.pojo.DTOAssembler.getAllIssuesDto;
import static edu.com.softserveinc.bawl.dto.pojo.DTOAssembler.getIssueDto;
import static edu.com.softserveinc.bawl.dto.pojo.DTOAssembler.getUserHistoryDtos;
import static edu.com.softserveinc.bawl.models.enums.IssueStatusHelper.getIssueStatusForAddIssue;
import static edu.com.softserveinc.bawl.models.enums.IssueStatusHelper.getIssueStatusForResolving;
import static edu.com.softserveinc.bawl.utils.MessageBuilder.getBaseURL;

/**
 * Controller for issue management
 */
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

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  @ResponseBody
  public IssueDTO getIssue(@PathVariable("id") int issueId) {
    return getIssueDto(historyService.getLastIssueByIssueID(issueId));
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
    return issueService.getById(id).map(issue -> getUserHistoryDtos(issue, userService))
        .orElse(Collections.<UserHistoryDTO>emptyList());
  }


  @PreAuthorize("hasRole('ROLE_MANAGER')")
  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<IssueDTO> getAllIssues() {
    return getAllIssuesDto(issueService.loadIssuesList());
  }

  @PreAuthorize("hasRole('ROLE_MANAGER')")
  @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseDTO deleteIssue(@PathVariable("id") int issueId, HttpServletRequest request) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      issueService.deleteProblem(issueId, userService.getCurrentUserId());
      mailService.notifyForIssue(issueId, "Issue has been deleted.", getBaseURL(request));
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
    return getAllIssuesDto(historyService.getLastUniqueIssues());
  }

  /**
   * Returns all issues with status RESOLVED
   *
   * @return list of all issues with status RESOLVED
   */
  @RequestMapping(value = "get_Resolved", method = RequestMethod.GET)
  @ResponseBody
  public List<IssueDTO> getResolvedIssues() {
    return DTOAssembler.getAllResolvedIssuesDto(historyService.getLastUniqueIssues());
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
      issueService.addIssue(request.getName(), request.getDescription(), request.getMapPointer(),
          request.getAttachments(), request.getCategory(), request.getPriorityId());
      responseDTO.setMessage(ISSUE_ADDED);
    } catch (Exception ex) {
      responseDTO.setMessage(ISSUE_NOT_ADDED);
    }
    return responseDTO;

  }


  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseDTO editIssue(@RequestBody IssueDTO issueDTO, HttpServletRequest request) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      issueService.getById(issueDTO.getId()).ifPresent(editedIssue -> {

        editedIssue = editedIssue.withStatus(issueDTO.getStatus()).withAttachments(issueDTO.getAttachments())
            .withDescription(issueDTO.getDescription()).withName(issueDTO.getName());

        categoryService.getCategoryByNameOrAddNew(issueDTO.getCategory());
        issueService.editProblem(editedIssue, userService.getCurrentUserId());
      });
      mailService.notifyForIssue(issueDTO.getId(), "Issue has been updated.", getBaseURL(request));
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
    } catch (Exception ex) {
      responseDTO.setMessage(NOT_AUTHORIZED);
    }
    return responseDTO;
  }



}
