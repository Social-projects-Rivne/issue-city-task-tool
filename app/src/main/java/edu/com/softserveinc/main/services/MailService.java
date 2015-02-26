package edu.com.softserveinc.main.services;

public interface MailService {
	
	public int notifyForIssue(int issueId, String msg);
	
	public int notifyForUser(int userId, String msg);

}
