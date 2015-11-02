package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

	public static final Logger LOG = Logger.getLogger(StatusController.class);

	@Autowired
	private SubscriptionService subscriptionService;


	// TODO: Return some nice UI, and correct statuses.
	// TODO: Maybe confirmation

    // addSubscription
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public  @ResponseBody Map <String, String> addSubscriptionAction(
			    @RequestBody SubscriptionModel sub, Map <String, String> message) {
		try {
			subscriptionService.create(sub);
			message.put("message", "New subscription was successfully added");
		} catch (Exception e) {
			message.put("message","Some problem occured! New subscription was NOT added");
		}
		return message;
	}

    @RequestMapping(value = "{id}/delete/{digest}", method = RequestMethod.POST)
    public  @ResponseBody Map <String, String> cancelSubscription(
            @PathVariable(value = "id") Integer id, @PathVariable(value = "digest") Integer digest,
            Map <String, String> message) {
        try {
            subscriptionService.delete(id);
            message.put("message", "New subscription was successfully added");
        } catch (Exception e) {
            message.put("message","Some problem occured! New subscription was NOT added");
        }
        return message;
    }


}
