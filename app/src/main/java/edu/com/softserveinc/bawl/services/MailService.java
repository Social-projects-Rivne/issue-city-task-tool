package edu.com.softserveinc.bawl.services;

public interface MailService {

	public void notifyForIssue(int issueId, String msg);

	public void notifyUser(int userId, String msg);

}
