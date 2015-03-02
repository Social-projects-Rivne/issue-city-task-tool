package edu.com.softserveinc.main.services;

public interface MailService {
	
	public void notifyForIssue(int issueId, String msg);
	
	public void notifyUser(int userId, String msg);

}
