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

import java.util.List;

import static edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl.getMandrillMail;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	public static final Logger LOG=Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IssueService issueService;

	@RequestMapping("/all")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody List<UserDTO> getUsersAction() {
		return DTOAssembler.getAllUsersDtoFrom(userService.loadUsersList());
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
    ResponseDTO addUserAction(@RequestBody UserDTO userDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
            UserModel userModel = new UserModel(userDTO.getName(), userDTO.getEmail(),
                    userDTO.getLogin(), userDTO.getRoleId(), userDTO.getPassword(), userDTO.getAvatar());
            userModel = userService.addUser(userModel);
			getMandrillMail().sendRegNotification(userModel);
			responseDTO.setMessage("Successfully registered. Please confirm your email");
		} catch (Exception ex) {
			responseDTO.setMessage("Some problem occured! User was not added");
		}
		return responseDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseDTO editUserAction(
			@RequestBody UserModel userModel) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			userService.editUser(userModel);
			getMandrillMail().sendSimpleMessage(MailPatterns.UPDATE_ACCOUNT_PATTERN, userModel, userModel.getLogin(), userModel.getRole().getCaption());
			responseDTO.setMessage("User was successfully edited");
		} catch (Exception ex) {
			responseDTO.setMessage("Some problem occurred! User was not updated" + ex.toString());
		}

		return responseDTO;
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody UserModel validateUser (
			@RequestBody UserDTO userDTO, UserModel userModel) {
		try {
			userModel = userService.getById(userDTO.getId());
			if (userModel.getPassword().equals(userDTO.getPassword())){
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
			if (adminModelList.size() > 1){
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
	public @ResponseBody UserDTO getCurrentUserAction(){
		try {
			String currentUserLoginName = getCurrentUser().getName();
			return DTOAssembler.getUserDtoFrom(userService.getByLogin(currentUserLoginName));
		} catch (Exception ex) {
			return new UserDTO();
		}
	}

	/* This metod send notification to email from #admin panel */
	@RequestMapping(value="send-notification", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO submittedFromData(
			@RequestBody UserNotificationDTO userNotificationDTO,
						 ResponseDTO responseDTO ) {

		String email = userNotificationDTO.getEmail();
		String messagePattern = userNotificationDTO.getMessage();
		String subject = userNotificationDTO.getSubject();
		String name = "User name";

		try { getMandrillMail().simpleEmailSender(email, name, subject, messagePattern);
			  responseDTO.setMessage("Mail has been sent");
		} catch (Exception e){responseDTO.setMessage("Error");}

		return responseDTO ;
	}

	@RequestMapping(value="/changename/{newname}", method = RequestMethod.GET)
	public @ResponseBody ResponseDTO changeUserName(@PathVariable int id,@PathVariable String newname){
		ResponseDTO responseDTO = new ResponseDTO();
		String currentUserLoginName = getCurrentUser().getName();
		UserModel userModel=userService.getByLogin(currentUserLoginName);
		userModel.setName(newname);
		userService.editUser(userModel);
		responseDTO.setMessage("Name has been succesfully edited");
		return  responseDTO ;
	}

	@RequestMapping(value="/changepass", method = RequestMethod.GET)
	public @ResponseBody ResponseDTO changeUserPassword(){
		ResponseDTO responseDTO = new ResponseDTO();
		UserModel userModel = getCurrentUser();
		String newPassword = PassGenerator.generate(1, 5);
		userModel.setPassword(newPassword);
		userService.editUserPass(userModel);
		getMandrillMail().sendSimpleMessage(MailPatterns.PASSWORD_RESET_PATTERN, userModel, userModel.getName());
		responseDTO.setMessage("Your pass have been changed ! Watch about it on your mail ! ");
		return responseDTO;
	}

    /**
     * Returns list of UserIssuesHistoryDto by user id
     * @return list of UserIssuesHistoryDto
     */
    @RequestMapping(value = "user/history", method = RequestMethod.GET)
    public @ResponseBody List<UserIssuesHistoryDTO> getUserIssuesHistories(){
        List<IssueModel> issues = issueService.loadIssuesList();
		UserModel userModel = getCurrentUser();
		List<HistoryModel> listOfHistoriesByUserID = historyService.getHistoriesByUserID(userModel.getId());
		return DTOAssembler.getAllUserIssuesHistoryDTO(listOfHistoriesByUserID, issues, userModel);
    }

    @RequestMapping(value = "user/history/issues", method = RequestMethod.GET)
    public @ResponseBody List<UserHistoryIssuesForUserDTO> getUserIssuesHistoriesForUser(){
        List<HistoryModel> historyModels = historyService.getHistoriesByUserID(getCurrentUser().getId());
        List<IssueModel> issueModels = issueService.loadIssuesList();
        return DTOAssembler.getUserIssueHistoryForUserDto(historyModels, issueModels);
    }

    private UserModel getCurrentUser(){
        String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getByLogin(currentUserLoginName);
    }
}






