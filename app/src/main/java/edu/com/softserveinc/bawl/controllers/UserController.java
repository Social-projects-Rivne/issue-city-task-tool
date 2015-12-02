package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.*;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.ImageService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.PassGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl.getMandrillMail;
import static edu.com.softserveinc.bawl.utils.MessageBuilder.getBaseURL;


/**
 * Controller for user manipulation
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

  public static final Logger LOG = Logger.getLogger(UserController.class);

  public static final String SUCCESS_DELETE = "User has been deleted";
  public static final String FAILURE_DELETE = "Failed. User hasn't been deleted.";

  @Autowired
  private UserService userService;

  @Autowired
  private HistoryService historyService;

  @Autowired
  private IssueService issueService;

  @Autowired
  private ImageService imageService;

  @RequestMapping("/all")
  @ResponseBody
  public List<UserDTO> getUsersAction() {
    return DTOAssembler.getAllUsersDtoFrom(userService.loadUsersList());
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public ResponseDTO addUserAction(@RequestBody UserDTO userDTO, HttpServletRequest request) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      UserModel userModel =
          new UserModel(userDTO.getName(), userDTO.getEmail(), userDTO.getLogin(), userDTO.getRoleId(),
              userDTO.getPassword(), userDTO.getAvatar());
      userModel = userService.addUser(userModel);
      getMandrillMail().sendRegNotification(userModel, getBaseURL(request));
      responseDTO.setMessage("Successfully registered. Please confirm your email");
    } catch (Exception ex) {
      responseDTO.setMessage("Some problem occured! User was not added");
    }
    return responseDTO;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseDTO editUserAction(@RequestBody UserDTO userDTO) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      UserModel userModel = new UserModel().withAvatar(userDTO.getAvatar()).withEmail(userDTO.getEmail())
              .withLogin(userDTO.getLogin()).withName(userDTO.getName()).withPassword(userDTO.getPassword()).withRole(UserRole.getByRoleId(userDTO.getRoleId()));
      userService.editUser(userModel);
      getMandrillMail().sendSimpleMessage(MailPatterns.UPDATE_ACCOUNT_PATTERN, userModel, userModel.getLogin(),
              userModel.getRole().getCaption());
      responseDTO.setMessage("User was successfully edited");
    } catch (Exception ex) {
      responseDTO.setMessage("Some problem occurred! User was not updated" + ex.toString());
    }

    return responseDTO;
  }

  @RequestMapping(value = "/validate", method = RequestMethod.POST)
  @ResponseBody
  public UserModel validateUser(@RequestBody UserDTO userDTO, UserModel userModel) {
    try {
      userModel = userService.getById(userDTO.getId());
      if (userModel.getPassword().equals(userDTO.getPassword())) {
        userModel.setRole(UserRole.USER);
        userService.editUser(userModel);
        return userModel;
      }
    } catch (Exception ex) {
      LOG.warn(ex);
    }
    return userModel;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public @ResponseBody ResponseDTO removeUserAction(
          @PathVariable int id) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
      List<UserModel> adminModelList = userService.getByRoleId(UserRole.ADMIN);
      if (adminModelList.size() > 1 || userService.getById(id).getRole() == UserRole.USER ||
              userService.getById(id).getRole() == UserRole.MANAGER ||
              userService.getById(id).getRole() == UserRole.SUBSCRIBER ){
        userService.deleteUser(id);
        UserModel userModel = userService.getById(id);
        getMandrillMail().sendSimpleMessage(MailPatterns.DELETE_ACCOUNT_PATTERN, userModel);
        responseDTO.setMessage("User was successfully deleted");
      } else {
        responseDTO.setMessage("Fail. At least one Admin must be in system!");
      }
    } catch (Exception ex) {
      responseDTO.setMessage("Some problem occured! User was not deleted");
    }
    return responseDTO;
  }

  @RequestMapping(value = "/current", method = RequestMethod.GET)
  @ResponseBody
  public UserDTO getCurrentUserAction() {
    try {
      return DTOAssembler.getUserDtoFrom(getCurrentUser());
    } catch (Exception ex) {
      return null;
    }
  }

  /* This metod send notification to email from #admin panel */
  @RequestMapping(value = "send-notification", method = RequestMethod.POST)
  @ResponseBody
  public UserDTO submittedFromData(@RequestBody UserNotificationDTO userNotificationDTO, UserDTO userDTO) {
    try {
      getMandrillMail().notifyForIssue(userNotificationDTO);
      return userDTO.withMessage("Mail has been sent");
    } catch (Exception e) {
      return userDTO.withMessage("Error");
    }
  }

//  @RequestMapping(value = "/changename/{name}", method = RequestMethod.GET)
//  @ResponseBody
//  public ResponseDTO changeUserName(@PathVariable int id, @PathVariable String name) {
//    ResponseDTO responseDTO = new ResponseDTO();
//    String currentUserLoginName = getCurrentUser().getName();
//    UserModel userModel = userService.getByLogin(currentUserLoginName);
//    userModel.setName(name);
//    userService.editUser(userModel);
//    responseDTO.setMessage("Name has been succesfully edited");
//    return responseDTO;
//  }

  @RequestMapping(value = "/changepass", method = RequestMethod.GET)
  @ResponseBody
  public ResponseDTO changeUserPassword() {
    UserModel userModel = getCurrentUser();
    String newPassword = PassGenerator.generate(1, 5);
    userModel.setPassword(newPassword);
    userService.editUserPass(userModel);
    getMandrillMail().sendSimpleMessage(MailPatterns.PASSWORD_RESET_PATTERN, userModel, userModel.getName());
    return new UserDTO().withMessage("Your pass have been changed ! Watch about it on your mail ! ");
  }

  /**
   * Returns list of UserIssuesHistoryDto by user id
   *
   * @return list of UserIssuesHistoryDto
   */
  @RequestMapping(value = "user/history", method = RequestMethod.GET)
  @ResponseBody
  public List<UserIssuesHistoryDTO> getUserIssuesHistories() {
    List<IssueModel> issues = issueService.loadIssuesList();
    UserModel userModel = getCurrentUser();
    List<HistoryModel> listOfHistoriesByUserID = historyService.getHistoriesByUserID(userModel.getId());
    return DTOAssembler.getAllUserIssuesHistoryDTO(listOfHistoriesByUserID, issues, userModel);
  }

  @RequestMapping(value = "user/history/issues", method = RequestMethod.GET)
  @ResponseBody
  public List<UserHistoryIssuesForUserDTO> getUserIssuesHistoriesForUser() {
    final List<HistoryModel> historyModels = historyService.getHistoriesByUserID(getCurrentUser().getId());
    final List<IssueModel> issueModels = issueService.loadIssuesList();
    return DTOAssembler.getUserIssueHistoryForUserDto(historyModels, issueModels);
  }

  private UserModel getCurrentUser() {
    final String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
    return userService.getByLogin(currentUserLoginName);
  }


}






