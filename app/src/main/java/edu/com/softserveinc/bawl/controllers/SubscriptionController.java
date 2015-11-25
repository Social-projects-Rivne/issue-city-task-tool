package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

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
			HttpServletRequest request
	) {

		String email = subscriptionDTO.getEmail();

		if (userService.isValidUser(email) == true) { // true if user is exist
			System.out.println("## User is Exist");

				int issueId = subscriptionDTO.getIssueId();
				existuserId = userService.getUserIdByEmail(email);
				subscriptionService.createSubscription(issueId, existuserId);
			//	subId = subscriptionModel.getId();	System.out.println("## SubId = "+subId);

				System.out.println("## Subscrip" + subscriptionService.createSubscription(issueId, existuserId));
			// TODO
			int id =  subscriptionModel.getId(); System.out.println("## SubId = "+id);
			getMandrillMail().sendSubNotification(subscriptionDTO, getBaseURL(request),id);

		} else { // false if user is not exist
			System.out.println("## User is not exist");

			UserModel userModel = new UserModel("Name1", subscriptionDTO.getEmail(), new Random().toString(), 4, "Pass", "Ava");
			userModel = userService.addSubscriber(userModel);
			subscriptionService.createSubscription(subscriptionDTO.getIssueId(), userModel.getId());
			responseDTO.setMessage("This subscription was alredy exist");

			// TODO
			int id =  subscriptionModel.getId(); System.out.println("## SubId = "+id);
			getMandrillMail().sendSubNotification(subscriptionDTO, getBaseURL(request),id);
			}

		return responseDTO;
	}

	@RequestMapping(value = "/{id}/validSub/{hash}", method = RequestMethod.GET)
	public  @ResponseBody ResponseDTO validation(
				@PathVariable(value = "id") Integer id,
				@PathVariable(value = "hash") Integer hash,
			ResponseDTO responseDTO,SubscriptionModel subscriptionModel ) {

		System.out.println("#####################################################################################");
		System.out.println("Hallo from Spring");
		System.out.println("#####################################################################################");
		System.out.println("id = "+id+" hash = "+hash);
;
		System.out.println("## hasH"+subscriptionService.getHashSubscription(id));

			if(hash.equals(subscriptionService.getHashSubscription(id))){
				subscriptionService.validateSubscription(subscriptionModel);
		}else{
			 System.out.println("Something wrong");
				return responseDTO;
		}


		try {
			subscriptionService.delete(id);
			responseDTO.setMessage(MESSAGE_TEXT_DELL +" "+ SUCCESS_DELL);
		} catch (Exception e) {
			responseDTO.setMessage(MESSAGE_TEXT_DELL + " "+ FAILURE_DELL);
		}
		return responseDTO;
	}

	@RequestMapping(value = "{id}/delete/{digest}", method = RequestMethod.POST)
	public  @ResponseBody ResponseDTO unSubscription(
			@PathVariable(value = "id") Integer id, @PathVariable(value = "digest") Integer digest,
			ResponseDTO responseDTO ) {
		try {
			subscriptionService.delete(id);
			responseDTO.setMessage(MESSAGE_TEXT_DELL +" "+ SUCCESS_DELL);
		} catch (Exception e) {
			responseDTO.setMessage(MESSAGE_TEXT_DELL + " "+ FAILURE_DELL);
		}
		return responseDTO;
	}

}
