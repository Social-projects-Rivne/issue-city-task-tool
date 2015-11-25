package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.DTOAssembler;
import edu.com.softserveinc.bawl.dto.pojo.IssueDTO;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.UserHistoryDTO;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

import static edu.com.softserveinc.bawl.dto.pojo.DTOAssembler.*;
import static edu.com.softserveinc.bawl.models.enums.IssueStatusHelper.getIssueStatusForResolving;
import static edu.com.softserveinc.bawl.utils.MessageBuilder.getBaseURL;

/**
 * Controller for issue management
 */
@RestController
@RequestMapping("issue")
public class IssueController {

  public static final Logger LOG = Logger.getLogger(IssueController.class);

  public static final String SUCCESS_ADDED = "Success. Issue was successfully added";
  public static final String FAILURE_ADDED  = "Failure. Some problem occured! Issue was not added";
  public static final String NOT_AUTHORIZED = "You are not authorized for this action";
  public static final String SUCCESS_UPDATE = "Success.Issue has been updated.";
  public static final String FAILURE_UPDATE = "Failure. Some problem with sending email. Try again and check your email";
  public static final String SUCCESS_MARKED = "Success. Issue has been marked as possibly resolved.";
  public static final String FAILURE_MARKED = "Failure. Issue hasn't been marked as possibly resolved.";


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
  @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseDTO deleteIssue(@PathVariable("id") int issueId, HttpServletRequest request) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      issueService.deleteProblem(issueId, userService.getCurrentUserId());
      mailService.notifyForIssue(issueId, "Issue has been deleted.", getBaseURL(request));
      responseDTO.setMessage(SUCCESS_UPDATE);
    } catch (Exception ex) {
      responseDTO.setMessage(FAILURE_UPDATE);
    }
    return responseDTO;
  }

  /**
   * Returns all issues except 'resolved' issue
   *
   * @return list of all issues
   */
  @RequestMapping(value = "all", method = RequestMethod.GET)
  @ResponseBody
  public List<IssueDTO> getIssues() {
    return getAllIssuesDto(historyService.getLastUniqueIssues());
  }

  /**
   * Returns all issues with status RESOLVED
   *
   * @return list of all issues with status RESOLVED
   */
  @RequestMapping(value = "resolved", method = RequestMethod.GET)
  @ResponseBody
  public List<IssueDTO> getResolvedIssues() {
    return DTOAssembler.getAllResolvedIssuesDto(historyService.getLastUniqueIssues());
  }

  /**
   * Adds new issue
   *
   * @param issueDTO
   * @return
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public ResponseDTO addIssue(@RequestBody IssueDTO issueDTO) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      issueService.addIssue(issueDTO.getName(), issueDTO.getDescription(), issueDTO.getMapPointer(),
              issueDTO.getAttachments(), issueDTO.getCategory(), issueDTO.getPriorityId());
      responseDTO.setMessage(SUCCESS_ADDED);
    } catch (Exception ex) {
      responseDTO.setMessage(FAILURE_ADDED);
    }
    return responseDTO;

  }


  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseDTO editIssue(@RequestBody IssueDTO issueDTO, HttpServletRequest request) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      IssueModel editedIssue = historyService.getLastIssueByIssueID(issueDTO.getId());
      CategoryModel category = categoryService.getCategoryByName(issueDTO.getCategory()).get();
      editedIssue = editedIssue.withStatus(issueDTO.getStatus()).withAttachments(issueDTO.getAttachments())
              .withDescription(issueDTO.getDescription()).withName(issueDTO.getName()).withCategory(category);

      issueService.editProblem(editedIssue, userService.getCurrentUserId());
      mailService.notifyForIssue(issueDTO.getId(), "Issue has been updated.", getBaseURL(request));
      responseDTO.setMessage(SUCCESS_UPDATE);
    } catch (Exception ex) {
      responseDTO.setMessage(FAILURE_UPDATE);
    }
    return responseDTO;
  }


  @RequestMapping(value = "resolve/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseDTO toResolve(@PathVariable("id") int id) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      IssueModel editedIssue = historyService.getLastIssueByIssueID(id);
      UserModel userModel = userService.getCurrentUser();
      editedIssue = editedIssue.withStatus(getIssueStatusForResolving(userModel.getRole()));
      issueService.editProblem(editedIssue, userModel.getId());
      responseDTO.setMessage(SUCCESS_MARKED);
    } catch (Exception ex) {
      responseDTO.setMessage(FAILURE_MARKED);
    }
    return responseDTO;
  }



}
