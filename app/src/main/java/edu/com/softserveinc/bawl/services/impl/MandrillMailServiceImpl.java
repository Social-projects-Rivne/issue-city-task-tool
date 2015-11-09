package edu.com.softserveinc.bawl.services.impl;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import com.cribbstechnologies.clients.mandrill.model.response.message.SendMessageResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillMessagesRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.MessageBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Collection;
import java.util.Properties;

@Service
public class MandrillMailServiceImpl implements MailService {

    public static final Logger LOG = Logger.getLogger(MandrillMailServiceImpl.class);

    private static MandrillRESTRequest request = new MandrillRESTRequest();
    private static MandrillConfiguration config = new MandrillConfiguration();
    private static MandrillMessagesRequest messagesRequest = new MandrillMessagesRequest();
    private static HttpClient client;
    private static ObjectMapper mapper = new ObjectMapper();
    private static Properties properties = new Properties();
    MandrillMessageRequest messageRequest;

    private static MandrillMailServiceImpl mailService = null;

    @Autowired
    private SubscriptionService subscriptionService;

    //TODO add this to get real url of application
    /*
    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }*/

    private static void initialize(){
        properties = MessageBuilder.getProperties();
        config.setApiKey(properties.getProperty("mail.api_key"));
        config.setApiVersion(properties.getProperty("mail.api_version"));
        config.setBaseURL(properties.getProperty("mail.base_url"));
        request.setConfig(config);
        request.setObjectMapper(mapper);
        messagesRequest.setRequest(request);
        client = new DefaultHttpClient();
        request.setHttpClient(client);
    }

    //TODO don't do this. you live in container so use beans approach
    public static MandrillMailServiceImpl getMandrillMail(){
        if (mailService == null){
            mailService = new MandrillMailServiceImpl();
            initialize(); // TODO must bi initialized by container
        }
        return mailService;
    }

    public void sendRegNotification(UserModel userModel){
        String link = properties.getProperty("mail.root_url") + properties.getProperty("mail.confirmation_url") +
                userModel.getPassword() + "&id=" + userModel.getId();
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MailPatterns.REGISTRATION_PATTERN, userModel.getName(), link)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();
        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    public void sendSimpleMessage(String pattern, UserModel userModel, String ... params){
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(pattern, params)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();
        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    public void sendMessageWithSubject(String pattern, String subject,  UserModel userModel){
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(pattern)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .setSubject(subject)
                .build();
        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    public void sendMessage(MandrillHtmlMessage mandrillMessage) {
        messageRequest = new MandrillMessageRequest();
        messageRequest.setMessage(mandrillMessage);
        try {
            SendMessageResponse response = messagesRequest.sendMessage(messageRequest);
        } catch (RequestFailedException e) {
            LOG.warn(e);
        }
    }

    @Override
    public void notifyForIssue(int issueId, String msg){
        Collection<SubscriptionModel> subs = subscriptionService.listByIssueId(issueId);
        for (SubscriptionModel sub: subs){
            String digest = DigestUtils.md5DigestAsHex(sub.toString().getBytes());
            String link = properties.getProperty("mail.base_url") + "subscriptions/" + sub.getId() + "/delete/" + digest;
            MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                    .setPattern(MailPatterns.NOTIFY_FOR_ISSUE_PATTERN, String.valueOf(sub.getIssueId()), msg, link)
                    .setRecipients(new MandrillRecipient("User", sub.getEmail()))
                    .build();
            sendMessage(mandrillMessage);
        }
    }
    /**
     * Seample Email Sender
     *
     * @param   email,
     * @param   name,
     * @param   subject,
     * @param   messagePattern
     */
    @Override
    public void  simpleEmailSender (String email, String name, String subject, String messagePattern){
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
            .setRecipients(new MandrillRecipient(name, email))
            .setSubject(subject)
            .setPattern(messagePattern)
            .build();
            MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
        }
}

