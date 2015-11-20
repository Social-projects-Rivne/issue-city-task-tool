package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.DTOAssembler;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.UserDTO;
import edu.com.softserveinc.bawl.dto.pojo.UserHistoryIssuesForUserDTO;
import edu.com.softserveinc.bawl.dto.pojo.UserIssuesHistoryDTO;
import edu.com.softserveinc.bawl.dto.pojo.UserNotificationDTO;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.PassGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

  @Autowired
  private UserService userService;

  @Autowired
  private HistoryService historyService;

  @Autowired
  private IssueService issueService;

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
  public ResponseDTO editUserAction(@RequestBody UserModel userModel) {
    ResponseDTO responseDTO = new ResponseDTO();
    try {
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
  @ResponseBody
  public UserDTO removeUserAction(@PathVariable int id) {
    try {
      if (userService.getByRoleId(UserRole.ADMIN).isEmpty()) {
        return new UserDTO().withMessage("Fail. At least one Admin must be in system!");
      } else {
        userService.deleteUser(id);
        UserModel userModel = userService.getById(id);
        getMandrillMail().sendSimpleMessage(MailPatterns.DELETE_ACCOUNT_PATTERN, userModel);
        return new UserDTO().withMessage("User was successfully deleted");
      }
    } catch (Exception ex) {
      return new UserDTO().withMessage("Some problem occured! User was not deleted");
    }
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
      String email = userNotificationDTO.getEmail();
      String messagePattern = userNotificationDTO.getMessage();
      String subject = userNotificationDTO.getSubject();
      String name = "User name";
      getMandrillMail().simpleEmailSender(email, name, subject, messagePattern);
      return userDTO.withMessage("Mail has been sent");
    } catch (Exception e) {
      return userDTO.withMessage("Error");
    }
  }

  @RequestMapping(value = "/changename/{name}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseDTO changeUserName(@PathVariable int id, @PathVariable String name) {
    ResponseDTO responseDTO = new ResponseDTO();
    String currentUserLoginName = getCurrentUser().getName();
    UserModel userModel = userService.getByLogin(currentUserLoginName);
    userModel.setName(name);
    userService.editUser(userModel);
    responseDTO.setMessage("Name has been succesfully edited");
    return responseDTO;
  }

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






