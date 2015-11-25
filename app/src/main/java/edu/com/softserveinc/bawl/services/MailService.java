package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.dto.pojo.UserNotificationDTO;
import edu.com.softserveinc.bawl.models.UserModel;

public interface MailService {

	void sendMessage(MandrillHtmlMessage message);

	void notifyForIssue(int issueId, String msg, String rootURL);

	void notifyForIssue( UserNotificationDTO notificationDTO);

	void sendRegNotification(UserModel userModel, String rootURL);

	public void  simpleEmailSender (String email, String name, String subject, String messagePattern);

	public void sendSubNotification(SubscriptionDTO subscriptionDTO,String rootURL, int subId);

}
