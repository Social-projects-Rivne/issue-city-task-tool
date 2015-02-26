package edu.com.softserveinc.main.services.impl;

import org.springframework.stereotype.Service;

import edu.com.softserveinc.main.services.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Override
	public int notifyForIssue(int issueId, String msg) {
//		TODO: Implement
		return 0;
	}

	@Override
	public int notifyForUser(int userId, String msg) {
//		TODO: Implement
		return 0; 
	}

}
