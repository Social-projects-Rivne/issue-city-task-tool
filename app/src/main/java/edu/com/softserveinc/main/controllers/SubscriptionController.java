package edu.com.softserveinc.main.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.SubscriptionModel;
import edu.com.softserveinc.main.services.SubscriptionService;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;

	//TODO: Return some nice UI, and correct statuses.
	//TODO: Maybe confirmation
	@RequestMapping(value = "/{issueId}/{email}/{digest}", method = RequestMethod.GET)
	public String cancelSubscriptionFromMailAction(@PathVariable Integer issueId,
			@PathVariable String email, @PathVariable String digest) {
	
		if (DigestUtils.md5DigestAsHex((issueId.toString()+email).getBytes()) != digest) {
			//bad request
			return "home";
		}

		if (issueId != 0) {
			service.delete(issueId, email);
			// Unsubscribed from issueId issue
			return "home";
		}
		service.delete(email);
		// Unsubscribed from all issues
		return "home";
	 }

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addSubscriptionAction(
			@RequestBody SubscriptionModel sub, Map<String, String> message) {
		try {
			service.create(sub);
			message.put("message", "New subscription was successfully added");
		} catch (Exception e) {
			message.put("message",
					"Some problem occured! New subscription was NOT added");
		}

		return message;
	}

}
