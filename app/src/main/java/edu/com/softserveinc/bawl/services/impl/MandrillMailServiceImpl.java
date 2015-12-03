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
import edu.com.softserveinc.bawl.dao.SubscriptionDao;
import edu.com.softserveinc.bawl.dto.pojo.SubscriptionDTO;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.MessageBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionDao subscriptionDao;

    //TODO add this to get real url of application
    /*
    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }*/

    private static void initialize() {
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
    public static MandrillMailServiceImpl getMandrillMail() {
        if (mailService == null) {
            mailService = new MandrillMailServiceImpl();
            initialize(); // TODO must bi initialized by container
        }
        return mailService;
    }

    @Override
    public void sendRegNotification(UserModel userModel, String rootURL) {
        String link = rootURL + properties.getProperty("mail.confirmation_url") +
                userModel.getPassword() + "&id=" + userModel.getId();
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MailPatterns.REGISTRATION_PATTERN, userModel.getName(), link)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();
        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    public void sendSimpleMessage(String pattern, UserModel userModel, String... params) {
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(pattern, params)
                .setRecipients(new MandrillRecipient(userModel.getName(), userModel.getEmail()))
                .build();
        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    @Override
    public void sendMessage(MandrillHtmlMessage mandrillMessage) {
        messageRequest = new MandrillMessageRequest();
        messageRequest.setMessage(mandrillMessage);
        try {
            SendMessageResponse response = messagesRequest.sendMessage(messageRequest);
        } catch (RequestFailedException e) {
            LOG.warn(e);
        }
    }

    @Override // TODO
    public void notifyForIssue(int issueId, String msg, String rootURL){
        UserModel userModel = new UserModel();
//        Collection<SubscriptionModel> subs = subscriptionService.listByIssueId(issueId);
//            String digest = org.springframework.util.DigestUtils.md5DigestAsHex(subs.toString().getBytes());
//            String link = rootURL + "subscriptions/"  + "/delete/" + digest;
            MandrillHtmlMessage mandrillMessage = new MessageBuilder()
//               /     .setPattern(MailPatterns.NOTIFY_FOR_ISSUE_PATTERN, String.valueOf(issueId), msg, link)
                    .setRecipient(userModel)
                    .build();
        System.out.println("## mandrillMessage" + mandrillMessage);
            sendMessage(mandrillMessage);
        }


    @Override // --> This method sending email from AdminPanel
    public void simpleEmailSender(String email, String name, String subject, String messagePattern) {
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setRecipients(new MandrillRecipient(name, email))
                .setSubject(subject)
                .setPattern(messagePattern)
                .build();
        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }


    @Override // --> This metod need for sending subsciptions
    public void sendSubNotification(SubscriptionDTO subscriptionDTO, String rootURL, int subId) {

        String email = subscriptionDTO.getEmail();
        String name = "Name";
        int issueId = subscriptionDTO.getIssueId();
        String hash = DigestUtils.md5Hex(email + subId + issueId);
        String link = rootURL + properties.getProperty("mail.subconfirmation_url") + hash + "&id=" + subId;

        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MailPatterns.REGISTRATION_PATTERN, name, link)
                .setRecipients(new MandrillRecipient(name, email))
                .build();
        MandrillMailServiceImpl.getMandrillMail().sendMessage(mandrillMessage);
    }

    @Override // --> This metod need for sending CommentNotiffication
    public void sendCommentNotiffication(String comment, int issueId) {
        System.out.println("## issueId = " + issueId);

        try {
            if(subscriptionDao.findByIssueId(issueId).isEmpty()){
                System.out.println("## IS Empty");
            }else {
                List<SubscriptionModel> subscriptionModels = subscriptionDao.findByIssueId(issueId);

                for (SubscriptionModel subscriptionModel : subscriptionModels) {
                    if (subscriptionModel.getId() == 1) {

                        String email = userService.getEmailByUserId(subscriptionModel.getUserId());
                        String messagePattern = "messagePattern";
                        simpleEmailSender(email, "Name", comment, messagePattern);

                    } else {
                        System.out.println("## Not found");
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println("Something wrong");}
    }
}