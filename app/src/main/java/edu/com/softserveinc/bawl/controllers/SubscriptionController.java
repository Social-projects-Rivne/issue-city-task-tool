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

import java.util.Random;

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
	public int userId;

	@Autowired
	private SubscriptionService subscriptionService;
//
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
							UserRole userRole ) {

		String email = subscriptionDTO.getEmail();

		if (userService.isExistingUser(email) == true) { // ????? LOL
			// Если есть в базе

			if (userService.getRole(email) == 4) { //
				// Если подписчикю. Создаю подписку

				try {
					int issueId = subscriptionDTO.getIssueId();
					subscriptionService.createSubscription(issueId, userId);
					subscriptionService.SendApproved(userId,issueId);
					responseDTO.setMessage("Подписка создана. Пожалуйста активируйте.");


				} catch (Exception ex) {
					responseDTO.setMessage("Mail has been sent");
				}
			} else {
				System.out.println(" Пользователь зарегистрирован в системе. Пожалуйста авторизируйтесь.");
				responseDTO.setMessage("Mail has been sent");
			}

		} else {
			// Если нет в базе. Создаю пользователя

			try {
				UserModel userModel = new UserModel("Name1", subscriptionDTO.getEmail(), new Random().toString(), 0, "Pass", "Ava");
				userModel = userService.addUser(userModel);
				//subscriptionService.createSubscription(subscriptionDTO.getIssueId(),userModel.getId());
				userId = userModel.getId();
			} catch (Exception ex) {
				//responseDTO.setMessage("Mail has been sent");}

				try {
					subscriptionService.createSubscription(subscriptionDTO.getIssueId(), userId);
				} catch (Exception ex1) {
					//responseDTO.setMessage("Mail has been sent"); }

				}
			}
		}return responseDTO;
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
