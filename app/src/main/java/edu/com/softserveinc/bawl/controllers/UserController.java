package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.*;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl;
import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.PassGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO addUserAction(@RequestBody UserDTO userDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
            UserModel userModel = new UserModel(userDTO.getName(), userDTO.getEmail(),
                    userDTO.getLogin(), userDTO.getRoleId(), userDTO.getPassword(), userDTO.getAvatar());
            userModel = userService.addUser(userModel);
			MandrillMailServiceImpl.getMandrillMail().sendRegNotification(userModel);
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
			String role = userModel.getRole().get();
			MandrillMailServiceImpl.getMandrillMail()
					.sendSimpleMessage(MailPatterns.UPDATE_ACCOUNT_PATTERN, userModel, userModel.getLogin(), role);
			responseDTO.setMessage("User was successfully edited");
		} catch (Exception ex) {
			responseDTO.setMessage("Some problem occurred! User was not updated" + ex.toString());
		}

		return responseDTO;
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody UserModel validateUser (@RequestBody UserDTO user) {
		UserModel dbModel = null;
		try {
			dbModel = userService.getById(user.getId());
			if (dbModel.getPassword().equals(user.getPassword())){
				dbModel.setRole(UserRole.USER);
				userService.editUser(dbModel);
				return dbModel;
			}
		} catch (Exception ex) {
			LOG.warn(ex);
		}
		return dbModel;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody ResponseDTO removeUserAction(
			@PathVariable int id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			userService.deleteUser(id);
			UserModel userModel = userService.getById(id);
			MandrillMailServiceImpl.getMandrillMail().sendSimpleMessage(MailPatterns.DELETE_ACCOUNT_PATTERN, userModel);
			responseDTO.setMessage("User was successfully deleted");
		} catch (Exception ex) {
			responseDTO.setMessage("Some problem occured! User was not deleted");
		}

		return responseDTO;
	}

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public @ResponseBody UserDTO getCurrentUserAction(){
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (currentUserLoginName.equals("anonymousUser")) {
			return null;
		} else {
			return DTOAssembler.getUserDtoFrom(userService.getByLogin(currentUserLoginName));
		}
	}

	 /* This metod send notification to email from #admin panel */
	@RequestMapping(value="send-notification", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO
	submittedFromData(@RequestBody UserNotificationDTO userNotificationDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		String message = userNotificationDTO.getMessage();
		String subject = userNotificationDTO.getSubject();
		UserModel userModel = new UserModel();
		userModel.setName("User name");
		userModel.setEmail(userNotificationDTO.getEmail());
		MandrillMailServiceImpl.getMandrillMail().sendMessageWithSubject(message, subject, userModel);
		responseDTO.setMessage("Mail has been sent");
		return responseDTO ;
	}

	@RequestMapping(value="/changename/{newname}", method = RequestMethod.GET)
	public @ResponseBody ResponseDTO changeUserName(@PathVariable int id,@PathVariable String newname){
		ResponseDTO responseDTO = new ResponseDTO();
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserModel userModel=userService.getByLogin(currentUserLoginName);
		userModel.setName(newname);
		userService.editUser(userModel);
		responseDTO.setMessage("Name has been succesfully edited");
		return  responseDTO ;
	}

	@RequestMapping(value="/changepass", method = RequestMethod.GET)
	public @ResponseBody ResponseDTO changeUserPassword(){
		ResponseDTO responseDTO = new ResponseDTO();
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserModel userModel = userService.getByLogin(currentUserLoginName);
		String newPassword = PassGenerator.generate(1, 5);
		userModel.setPassword(newPassword);
		userService.editUserPass(userModel);
		MandrillMailServiceImpl.getMandrillMail()
				.sendSimpleMessage(MailPatterns.PASSWORD_RESET_PATTERN, userModel, userModel.getName());
		responseDTO.setMessage("Your pass have been changed ! Watch about it on your mail ! ");
		return responseDTO;
	}

    /**
     * Returns list of UserIssuesHistoryDto by user id
     * @param id user id
     * @return list of UserIssuesHistoryDto
     */
    @RequestMapping(value = "user/history", method = RequestMethod.GET)
    public @ResponseBody List<UserIssuesHistoryDTO> getUserIssuesHistories(@PathVariable int id){
        List<HistoryModel> listOfHistoriesByUserID = historyService.getHistoriesByUserID(id);
        List<IssueModel> issues = issueService.loadIssuesList();
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserModel userModel = userService.getByLogin(currentUserLoginName);

        return DTOAssembler.getAllUserIssuesHistoryDTO(listOfHistoriesByUserID, issues, userModel);
    }
}






