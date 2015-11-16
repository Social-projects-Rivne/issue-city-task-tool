package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

/* TODO: Mayby need a property file for messages ? */

	@Autowired
	private SubscriptionService subscriptionService;


	// TODO: Return some nice UI, and correct statuses.
	// TODO: Maybe confirmation

	// TODO	 NOTE ! : This class still refactored in near future



	@RequestMapping(value="/add", method = RequestMethod.POST)
	public @ResponseBody ResponseDTO addSubscriptionAction(
			@RequestBody
						 UserModel userModel,
						 SubscriptionDTO subscriptionDTO,
						 ResponseDTO responseDTO,
							UserService userService) {

		if ( userService.isExistingUser(subscriptionDTO.getEmail())== true){

		System.out.print("Пользователя не существуют");

		} else {

		}

		String name =  "User name";
		String subject = "Sibscription email validation";
		int id = 10;
		int hash = (subscriptionDTO.getEmail()+subscriptionDTO.getId()).hashCode();
		String messagePattern = "http://localhost:8085/"+"#subscriptions"+id+"/valid/"+hash;
		String email ="";

		MandrillMailServiceImpl.getMandrillMail().simpleEmailSender(email,name,subject,messagePattern);

		responseDTO.setMessage("Mail has been sent");
		return responseDTO ;
		}


	@RequestMapping(value = "{id}/valid/{hash}", method = RequestMethod.POST)
	public  @ResponseBody ResponseDTO validation(
				@PathVariable(value = "id") Integer id,
				@PathVariable(value = "hash") Integer hash,
			ResponseDTO responseDTO ) {
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
