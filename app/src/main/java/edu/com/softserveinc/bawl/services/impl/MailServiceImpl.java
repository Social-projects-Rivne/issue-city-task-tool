/*package edu.com.softserveinc.bawl.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.services.UserService;

import org.apache.log4j.Logger;

@Service
public class MailServiceImpl implements MailService {


    public static final Logger LOG=Logger.getLogger(MailServiceImpl.class);

	@Autowired private JavaMailSender mailSender;
	
	@Autowired private SubscriptionService subService;
	@Autowired private UserService userService;
	
	final String BAWL_EMAIL = "bawlrivne@gmail.com";
	final String HEADER = "<html><body><h3>Bawl notification for issue #";
	final String AFTER_ISSUE_ID = "</h3><br /><p>";
	final String BEFORE_LINK = "</p><br /><p>You may <a href=";
	final String AFTER_LINK = ">unsubscribe</a> at any time.</p></body></html>";
	
	private MimeMessage composeUserMessage(String to, String msg)
			throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, false);
		helper.setFrom(BAWL_EMAIL);
		helper.setSubject("Bawl user notification");
		helper.setTo(to);
		helper.setText("Bawl user notification\n\n" + msg, false);
		
		return message;
	}

	private MimeMessage composeIssueMessage(SubscriptionModel sub, String msg)
			throws MessagingException {

		String digest = DigestUtils.md5DigestAsHex(sub.toString().getBytes());
		String link = "http://localhost:8080/Bawl/subscriptions/" + sub.getId() + "/delete/" + digest;
				
		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, false);
		helper.setFrom(BAWL_EMAIL);
		helper.setSubject("Bawl notification");
		helper.setTo(sub.getEmail());
		helper.setText(HEADER+sub.getIssueId()+AFTER_ISSUE_ID+msg+BEFORE_LINK+link+AFTER_LINK, true);
		
		return message;
	}
	
	@Override
	public void notifyForIssue(int issueId, String msg) {
		Collection<SubscriptionModel> subs = subService.listByIssueId(issueId);
		for (SubscriptionModel sub: subs){
			try {
				MimeMessage composedMessage = composeIssueMessage(sub, msg);
				mailSender.send(composedMessage);
			} catch (MessagingException ex) {
				System.out.println("Mail problem: " + ex.getCause());
			}
		}
		
	}

	@Override
	public void notifyUser(int userId, String msg) {
		try {
			MimeMessage composedMessage = composeUserMessage(userService.getById(userId).getEmail(), msg);
			mailSender.send(composedMessage);
		} catch (MessagingException ex) {
			System.out.println("Mail problem: " + ex.getCause());
		}
	}

}
*/