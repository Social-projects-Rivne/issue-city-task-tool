package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;

public interface MailService {

	public void notifyForIssue(int issueId, String msg) ;

	public void sendMessage(MandrillHtmlMessage message);

	public void  simpleEmailSender (String email,String name, String subject, String  messagePattern);

}
