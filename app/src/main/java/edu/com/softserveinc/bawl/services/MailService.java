package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import edu.com.softserveinc.bawl.exception.MailSendException;

public interface MailService {

	public void notifyForIssue(int issueId, String msg) throws RequestFailedException, MailSendException;

	public void sendMessage(MandrillHtmlMessage message) throws RequestFailedException, MailSendException;

}
