package edu.com.softserveinc.bawl.services;

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

public class MandrillMailServiceTest extends AbstractBawlTest{

    private final String EMAIL = "elvissrivne@gmail.com";
    private final String MESSAGE_PATTERN = "some text";
    final int ISSUE_ID = 1;

    MandrillMailServiceImpl mailService;

    @Before
    public void setup(){
        mailService = MandrillMailServiceImpl.getMandrillMail();
    }

    @Test
    @Ignore
    public void sendRegNotification_shouldSendNotification(){
        UserModel userModel = new UserModel();
        userModel.setEmail(EMAIL);
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MESSAGE_PATTERN)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();
        mailService.sendMessage(mandrillMessage);
    }

    @Test
    @Ignore
    //some problems with subscriptions service
    public void notifyForIssue_shouldSendNotificationToUsers(){
        mailService.notifyForIssue(ISSUE_ID, MESSAGE_PATTERN);
    }


}
