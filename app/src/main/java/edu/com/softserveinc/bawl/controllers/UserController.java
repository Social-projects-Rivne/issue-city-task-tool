package edu.com.softserveinc.bawl.controllers;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.dto.UserDTO;
import edu.com.softserveinc.bawl.dto.UserIssuesHistoryDto;
import edu.com.softserveinc.bawl.dto.UserNotificationDto;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl;
import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.MessageBuilder;
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
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	/**
	 * Logger field
	 */
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
	public @ResponseBody Map<String, String> addUserAction(
			@RequestBody UserModel user, Map<String, String> message) {

		try {

			userService.addUser(user);
			UserModel dbModel = userService.getByLogin(user.getLogin());
			Properties properties = MessageBuilder.getProperties();
			String link = properties.getProperty("mail.root_url") + properties.getProperty("mail.confirmation_url") +
					dbModel.getPassword() + "&id=" + dbModel.getId();
			MandrillHtmlMessage mandrillMessage = new MessageBuilder()
					.setPattern(MailPatterns.REGISTRATION_PATTERN, dbModel.getName(), link)
					.setRecipients(new MandrillRecipient(dbModel.getName(), dbModel.getEmail()))
					.build();
			MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);

		} catch (RequestFailedException e) {
			message.put("message", "Some problem with sending email. Try again and check your email");
		} catch (Exception ex) {
			message.put("message", "Some problem occured! User was not added");
		}
		message.put("message", "Successfully registered. Please confirm your email");

		return message;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, String> editUserAction(
			@RequestBody UserModel userModel, Map<String, String> message) {

		try {
			userService.editUser(userModel);
			String role = userModel.getRole().get();
			MandrillHtmlMessage mandrillMessage = new MessageBuilder()
					.setPattern(MailPatterns.UPDATE_ACCOUNT_PATTERN, userModel.getLogin(), role)
					.setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
					.build();
			MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
			message.put("message", "User was successfully edited");
		} catch (RequestFailedException e) {
			message.put("message", "Some problem with sending email. Try again and check your email");
		} catch (Exception ex) {
			message.put("message", "Some problem occurred! User was not updated" + ex.toString());
		}

		return message;
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody UserModel validateUser (@RequestBody UserModel user) {

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
	public @ResponseBody Map<String, String> removeUserAction(
			@PathVariable int id, Map<String, String> message) {

		try {
			userService.deleteUser(id);
			UserModel userModel = userService.getById(id);
			MandrillHtmlMessage mandrillMessage = new MessageBuilder()
					.setPattern(MailPatterns.DELETE_ACCOUNT_PATTERN)
					.setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
					.build();
			MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
			message.put("message", "User was successfully deleted");
		} catch (RequestFailedException e) {
			message.put("message", "Some problem with sending email. Try again and check your email");
		} catch (Exception ex) {
			message.put("message", "Some problem occured! User was not deleted");
		}

		return message;
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
	public @ResponseBody Map<String, String>
	submittedFromData(@RequestBody UserNotificationDto userNotificationModel, Map<String, String> message) {
		MandrillHtmlMessage mandrillMessage = new MessageBuilder()
					.setPattern(userNotificationModel.getMessage())
					.setRecipients(new MandrillRecipient("Bawl user", userNotificationModel.getEmail()))
					.setSubject(userNotificationModel.getSubject())
					.build();
		try {
			MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
		} catch (RequestFailedException e) {
			message.put("message", "Some problem with sending email. Try again and check your email");
		}
		return message ;
	}

	@RequestMapping(value="/changename/{newname}", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> changeUserName(@PathVariable int id,@PathVariable String newname, Map<String, String> message){
		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserModel userModel=userService.getByLogin(currentUserLoginName);
		userModel.setName(newname);
		userService.editUser(userModel);
		message.put("message", "Name was succesfully edited");
		return  message ;
	}

	@RequestMapping(value="/changepass", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> changeUserPassword(Map<String, String> message){

		String currentUserLoginName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserModel userModel = userService.getByLogin(currentUserLoginName);
		String newPassword = PassGenerator.generate(1, 5);
		userModel.setPassword(newPassword);
		MandrillHtmlMessage mandrillMessage = new MessageBuilder()
				.setPattern(MailPatterns.PASSWORD_RESET_PATTERN, userModel.getName())
				.setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
				.build();
		try {
			MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
		} catch (RequestFailedException e) {
			message.put("message", "Some problem with sending email. Try again and check your email");
		}
		userService.editUserPass(userModel);
		message.put("message" , "Your pass have been changed ! Watch about it on your mail ! ");
		return message;
	}



    /**
     * Returns list of UserIssuesHistoryDto by user id
     * @param id user id
     * @return list of UserIssuesHistoryDto
     */
    @RequestMapping(value = "/{id}/history", method = RequestMethod.GET)
    public @ResponseBody List<UserIssuesHistoryDto> getUserIssuesHistories(@PathVariable int id){

        List<HistoryModel> listOfHistoriesByUserID = historyService.getHistoriesByUserID(id);
        List<IssueModel> issues = issueService.loadIssuesList();
        UserModel userModel = userService.getById(id);

        return DTOAssembler.getAllUserIssuesHistoryDTO(listOfHistoriesByUserID, issues, userModel);

    }

}






