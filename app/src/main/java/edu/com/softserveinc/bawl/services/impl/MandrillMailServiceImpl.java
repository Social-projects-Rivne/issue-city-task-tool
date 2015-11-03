package edu.com.softserveinc.bawl.services.impl;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import com.cribbstechnologies.clients.mandrill.model.response.message.MessageResponse;
import com.cribbstechnologies.clients.mandrill.model.response.message.SendMessageResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillMessagesRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.com.softserveinc.bawl.exception.MailSendException;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
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
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by Illia on 9/25/2015.
 */
@Service
public class MandrillMailServiceImpl implements MailService {

    /**
     *  Logger field
     */
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
    public void notifyForIssue(int issueId, String msg) throws RequestFailedException {
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



}
