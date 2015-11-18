package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import edu.com.softserveinc.bawl.models.UserModel;

public interface MailService {

	void sendMessage(MandrillHtmlMessage message);

	void notifyForIssue(int issueId, String msg, String rootURL);

	void sendRegNotification(UserModel userModel, String rootURL);

	void  simpleEmailSender (String email,String name, String subject, String  messagePattern);

}
