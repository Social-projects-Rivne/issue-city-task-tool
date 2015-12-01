package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.dto.pojo.ValidationDTO;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl.getMandrillMail;
import static edu.com.softserveinc.bawl.utils.MessageBuilder.getBaseURL;


@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

	public static final Logger LOG = Logger.getLogger(StatusController.class);

	public static final String MESSAGE_TEXT_ADD = "Thank you for you subscription! On you email sends letter with further instructions ";
	public static final String SUCCESS_ADD = "You have successfully subscribe. Have a nice day.";
	public static final String FAILURE_ADD = "This link has been corrupted";
	public static final String FAILURE_UNKNOWN = "Something wrong";
	public static final String SUCCESS_DELL = "You have successfully Unsubscribe. Have a nice day.";

	public int existuserId;

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private UserService userService;

	// TODO: Return some nice UI, and correct statuses.
	// TODO: Maybe confirmation
	// TODO	 NOTE ! : This class still refactored in near future

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public @ResponseBody
	ResponseDTO addSubscriptionAction(
			@RequestBody	SubscriptionDTO subscriptionDTO,
			 				ResponseDTO responseDTO,
							HttpServletRequest request) {

		String email = subscriptionDTO.getEmail();

		if (userService.isValidUser(email) == true) { LOG.info("## User Exist");

			int issueId = subscriptionDTO.getIssueId();
			existuserId = userService.getUserIdByEmail(email);
			subscriptionService.createSubscription(issueId, existuserId);
			int id = subscriptionService.getSubscriptionId(subscriptionDTO.getIssueId(),existuserId);
			getMandrillMail().sendSubNotification(subscriptionDTO, getBaseURL(request), id);
			responseDTO.setMessage(MESSAGE_TEXT_ADD);

		} else { LOG.info("## User is not exist");

			UserModel userModel = new UserModel("USER_NAME", subscriptionDTO.getEmail(), email.substring(0, email.indexOf("@")), 4, "Pass", null);
			userModel = userService.addSubscriber(userModel);
			subscriptionService.createSubscription(subscriptionDTO.getIssueId(), userModel.getId());

			int id = subscriptionService.getSubscriptionId(subscriptionDTO.getIssueId(),userModel.getId());
			getMandrillMail().sendSubNotification(subscriptionDTO, getBaseURL(request),id);
			responseDTO.setMessage(MESSAGE_TEXT_ADD);
			}
		return responseDTO;
	}

		@RequestMapping(value = "/valid", method = RequestMethod.POST)
		@ResponseBody
		public ResponseDTO validation (
				@RequestBody ValidationDTO validationDTO,
							 ResponseDTO responseDTO) {

			int subId = validationDTO.getId();
			String hash = validationDTO.getHash();
			String compareHash = (subscriptionService.getHashSubscription(subId)).toString();

		LOG.info("## " + subId); LOG.info("## " + hash); LOG.info("## compareHash = " + compareHash);

		try {
			if (compareHash.equals(hash)) {
				subscriptionService.validateSubscription ( subId );
				responseDTO.setMessage(SUCCESS_ADD);
				LOG.info("## " + SUCCESS_ADD);
			}else{
				System.out.println("## " +FAILURE_ADD);
				responseDTO.setMessage(FAILURE_ADD);
			}
		} catch (Exception ex) { LOG.warn(ex);
			responseDTO.setMessage(FAILURE_UNKNOWN);
		}
		return responseDTO;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO deleteSub (
			@RequestBody ValidationDTO validationDTO,
			ResponseDTO responseDTO) {

		int subId = validationDTO.getId();
		String hash = validationDTO.getHash();
		String compareHash = (subscriptionService.getHashSubscription(subId)).toString();

		LOG.info("## " + subId); LOG.info("## " + hash);LOG.info("## compareHash = " + compareHash);

		try {
			if (compareHash.equals(hash)) {
				subscriptionService.UnSubscription(subId);
				responseDTO.setMessage(SUCCESS_DELL);
				LOG.info("## "+SUCCESS_DELL);
			}else{
				LOG.info("##" + FAILURE_ADD);
				responseDTO.setMessage(FAILURE_ADD);
			}
		} catch (Exception ex) {
			LOG.warn(ex);
			responseDTO.setMessage(FAILURE_UNKNOWN);
		}
		return responseDTO;
	}
}
