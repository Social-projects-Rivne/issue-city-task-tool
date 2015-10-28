package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import edu.com.softserveinc.bawl.AbstractBawlTest;
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

/**
 * Created by Illia on 10/27/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MandrillMailServiceTest extends AbstractBawlTest{

    @Autowired
    MandrillMailServiceImpl mailService;

    @Before
    public void setup(){
        mailService = MandrillMailServiceImpl.getMandrillMail();
    }

    @Test
    @Ignore
    public void sendRegNotification_shouldSendNotification(){
        UserModel userModel = new UserModel();
        userModel.setName("mail test");
        userModel.setEmail("elvissrivne@gmail.com");
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern("some text")
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();
        mailService.sendMessage(mandrillMessage);
    }

}
