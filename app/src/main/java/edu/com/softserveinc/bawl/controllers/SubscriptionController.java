package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.dto.pojo.ValidationDTO;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
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

	public static final String MESSAGE_TEXT_ADD = "New subscription";
	public static final String SUCCESS_ADD = "was successfully added";
	public static final String FAILURE_ADD = "was NOT added";

	public static final String MESSAGE_TEXT_DELL = "The subscription";
	public static final String SUCCESS_DELL = "was successfully delited";
	public static final String FAILURE_DELL = "Some problem occured! was NOT added";
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
							SubscriptionModel subscriptionModel,
							UserRole userRole ,
							HttpServletRequest request) {

		String email = subscriptionDTO.getEmail();

		if (userService.isValidUser(email) == true) { System.out.println("## User Exist");

			int issueId = subscriptionDTO.getIssueId();
			existuserId = userService.getUserIdByEmail(email);
			subscriptionService.createSubscription(issueId, existuserId);
			int id = subscriptionService.getSubscriptionId(subscriptionDTO.getIssueId(),existuserId);
			getMandrillMail().sendSubNotification(subscriptionDTO, getBaseURL(request), id);
			responseDTO.setMessage("You have now been added to the mailing list. Please Confirm Your Email");

		} else { System.out.println("## User is not exist");

			UserModel userModel = new UserModel("Name1", subscriptionDTO.getEmail(), email.substring(0, email.indexOf("@")), 4, "Pass", "Ava");
			userModel = userService.addSubscriber(userModel);
			subscriptionService.createSubscription(subscriptionDTO.getIssueId(), userModel.getId());

			int id = subscriptionService.getSubscriptionId(subscriptionDTO.getIssueId(),userModel.getId());
			getMandrillMail().sendSubNotification(subscriptionDTO, getBaseURL(request),id);
			responseDTO.setMessage("Something wrong");
			}

		return responseDTO;
	}

		@RequestMapping(value = "/valid", method = RequestMethod.POST)
		@ResponseBody
		public ResponseDTO validation (
				@RequestBody ValidationDTO validationDTO,
							 SubscriptionModel subscriptionModel,
							 ResponseDTO responseDTO) {

			int subId = validationDTO.getId();
			String hash = validationDTO.getHash();
			String compareHash = (subscriptionService.getHashSubscription(subId)).toString();

		System.out.println("## "+subId);
		System.out.println("## "+hash);
		System.out.println("## compareHash = " + compareHash);

		try {
			if (compareHash.equals(hash)) {
				subscriptionService.validateSubscription ( subId );
				responseDTO.setMessage("You have successfully signed. Have a nice day.");
				System.out.println("## You have successfully signed. Have a nice day");
			}else{
				System.out.println("## The link has been corupted");
				responseDTO.setMessage("Hash is not OK");

			}
		} catch (Exception ex) {
			LOG.warn(ex);
			responseDTO.setMessage("Something wrong");
		}
		return responseDTO;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO deleteSub (
			@RequestBody ValidationDTO validationDTO,
			SubscriptionModel subscriptionModel,
			ResponseDTO responseDTO) {

		int subId = validationDTO.getId();
		String hash = validationDTO.getHash();
		String compareHash = (subscriptionService.getHashSubscription(subId)).toString();

		System.out.println("## "+subId);
		System.out.println("## "+hash);
		System.out.println("## compareHash = " + compareHash);

		try {
			if (compareHash.equals(hash)) {

				SubscriptionModel subscriptionModel1 = subscriptionService.getById(subId);
				System.out.println("## subscriptionModel1 = "+subscriptionModel1);
				subscriptionModel1.setIsValid(true);
				subscriptionService.editSubscription(subscriptionModel1);

			}else{
				System.out.println("## Something wrong");
				responseDTO.setMessage("Hash is not OK");
			}
		} catch (Exception ex) {
			LOG.warn(ex);
		}
		return responseDTO;
	}
}
