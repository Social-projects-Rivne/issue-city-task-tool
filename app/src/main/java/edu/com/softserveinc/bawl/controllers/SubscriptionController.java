package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.ResponseDTO;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
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

    // addSubscription
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public  @ResponseBody ResponseDTO addSubscriptionAction(
			@RequestBody SubscriptionModel subscriptionModel, ResponseDTO responseDTO) {
		try {
			subscriptionModel.setIsValid(false);
			subscriptionService.create(subscriptionModel);

			responseDTO.setMessage(MESSAGE_TEXT_ADD +" "+ SUCCESS_ADD);

		} catch (Exception e) {
			responseDTO.setMessage(MESSAGE_TEXT_ADD +" "+ FAILURE_ADD);
		} return responseDTO;
	}

    @RequestMapping(value = "{id}/delete/{digest}", method = RequestMethod.POST)
    public  @ResponseBody ResponseDTO cancelSubscription(
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
