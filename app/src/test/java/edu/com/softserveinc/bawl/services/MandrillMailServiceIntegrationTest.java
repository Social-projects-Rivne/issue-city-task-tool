package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl;

import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.MessageBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Properties;

public class MandrillMailServiceIntegrationTest extends AbstractBawlTest{

    private final String RIGHT_EMAIL = "elvissrivne@gmail.com";
    private final String WRONG_EMAIL = "123@123.123";

    private final String MESSAGE_PATTERN = "some text";
    private final String USERNAME = "Username";
    private final String PASSWORD = "1234";
    final int ISSUE_ID = 1;
    final int USER_ID = 1;

    MandrillMailServiceImpl mailService;

    @Before
    public void setup(){
        mailService = MandrillMailServiceImpl.getMandrillMail();
    }

    @Test
    //@Ignore
    public void sendRegNotification_WithRightEmail_shouldSendNotification() throws RequestFailedException {
        UserModel userModel = getValidUserModel();
        Properties properties = MessageBuilder.getProperties();
        String link = properties.getProperty("mail.root_url") + properties.getProperty("mail.confirmation_url") +
                userModel.getPassword() + "&id=" + userModel.getId();
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MailPatterns.REGISTRATION_PATTERN, userModel.getName(), link)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();

        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    @Test(expected=RequestFailedException.class)
    public void sendRegNotification_WithWrongEmail_shouldThrowException() throws RequestFailedException {
        UserModel userModel = getNotValidUserModel();
        Properties properties = MessageBuilder.getProperties();
        String link = properties.getProperty("mail.root_url") + properties.getProperty("mail.confirmation_url") +
                userModel.getPassword() + "&id=" + userModel.getId();
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MailPatterns.REGISTRATION_PATTERN, userModel.getName(), link)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();

        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    @Test
    @Ignore
    //some problems with subscriptions service
    public void notifyForIssue_shouldSendNotificationToUsers() throws RequestFailedException {
        mailService.notifyForIssue(ISSUE_ID, MESSAGE_PATTERN);
    }

    private UserModel getValidUserModel(){
        UserModel userModel = new UserModel();
        userModel.setId(USER_ID);
        userModel.setName(USERNAME);
        userModel.setEmail(RIGHT_EMAIL);
        userModel.setPassword(PASSWORD);
        return userModel;
    }

    private UserModel getNotValidUserModel(){
        UserModel userModel = new UserModel();
        userModel.setId(USER_ID);
        userModel.setName(USERNAME);
        userModel.setEmail(WRONG_EMAIL);
        userModel.setPassword(PASSWORD);
        return userModel;
    }






}
