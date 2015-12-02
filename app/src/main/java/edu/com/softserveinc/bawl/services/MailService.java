package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.models.UserModel;

public interface MailService {

	public void notifyForIssue(int issueId, String msg, String rootURL);

	void sendMessage(MandrillHtmlMessage message);

	public void sendCommentNotiffication(String comment, int issueId);

	void sendRegNotification(UserModel userModel, String rootURL);

	public void  simpleEmailSender (String email, String name, String subject, String messagePattern);

	public void sendSubNotification(SubscriptionDTO subscriptionDTO,String rootURL, int subId);

}
