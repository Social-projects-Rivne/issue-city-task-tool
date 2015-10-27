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
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.UserService;
import edu.com.softserveinc.bawl.utils.MailPatterns;
import edu.com.softserveinc.bawl.utils.MessageBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Properties;

/**
 * Created by Illia on 9/25/2015.
 */
@Service
public class MandrillMailServiceImpl implements MailService {

    /**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(MandrillMailServiceImpl.class);

    private static MandrillRESTRequest request = new MandrillRESTRequest();
    private static MandrillConfiguration config = new MandrillConfiguration();
    private static MandrillMessagesRequest messagesRequest = new MandrillMessagesRequest();
    private static HttpClient client;
    private static ObjectMapper mapper = new ObjectMapper();
    private static Properties properties = new Properties();

    private static MandrillMailServiceImpl mailService = null;

    @Autowired
    private UserService userService;

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

    public static MandrillMailServiceImpl getMandrillMail(){
        if (mailService == null){
            mailService = new MandrillMailServiceImpl();
            initialize();
        }
        return mailService;
    }

    public void sendRegNotification(UserModel model){
        String link = properties.getProperty("mail.root_url") + properties.getProperty("mail.confirmation_url");
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MailPatterns.REGISTRATION_PATTERN, model.getName(), link)
                .setRecipients(new MandrillRecipient[] {new MandrillRecipient(model.getName(), model.getEmail())})
                .build();
        sendMessage(mandrillMessage);
    }

    private void sendMessage(MandrillHtmlMessage message){
        MandrillMessageRequest mmr = new MandrillMessageRequest();
        mmr.setMessage(message);
        try {
            SendMessageResponse response = messagesRequest.sendMessage(mmr);
        } catch (RequestFailedException e) {
            LOG.warn(e);
        }
    }

	public void sendPasswordToUser(UserModel model, String pass){
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(MailPatterns.PASSWORD_RESET_PATTERN, model.getName())
                .setRecipients(new MandrillRecipient[] {new MandrillRecipient(model.getName(), model.getEmail())})
                .build();
        sendMessage(mandrillMessage);
    }

    @Override
    public void notifyForIssue(int issueId, String msg) {

    }

    @Override
    public void notifyUser(int userId, String message) {
        UserModel userModel = userService.getById(userId);
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(message)
                .setRecipients(new MandrillRecipient[] {new MandrillRecipient(userModel.getName(), userModel.getEmail())})
                .build();
        sendMessage(mandrillMessage);
    }
    @Override
    public void notifyByAdmin(String email,String subject,String Message ) {
        MandrillHtmlMessage mandrillMessage = new MessageBuilder()
                .setPattern(Message)
                .setRecipients(new MandrillRecipient[] {new MandrillRecipient("Admin", email)})
                .setSubject(subject)
                .build();
        sendMessage(mandrillMessage);
    }

}
