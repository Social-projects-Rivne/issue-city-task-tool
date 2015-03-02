package edu.com.softserveinc.main.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import edu.com.softserveinc.main.services.MailService;
import edu.com.softserveinc.main.services.SubscriptionService;
import edu.com.softserveinc.main.services.UserService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired private JavaMailSenderImpl mailSender;
	
	@Autowired private SubscriptionService subService;
	@Autowired private UserService userService;
	
	final String BAWL_EMAIL = "bawlrivne@gmail.com";
	
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

	@Override
	public void notifyForIssue(int issueId, String msg) {

		
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
