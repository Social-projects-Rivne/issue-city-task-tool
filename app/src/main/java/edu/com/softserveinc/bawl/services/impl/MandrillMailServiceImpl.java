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
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by Illia on 9/25/2015.
 */
@Service
public class MandrillMailServiceImpl implements MailService {

    /**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(MandrillMailServiceImpl.class);


    private final static String rootUrl = "http://localhost:8080/";
    private final static String MAIN_URL = rootUrl + "#email-confirm/";
    private final static String API_KEY = "MJ7XduK_1GX6JxuNezXYjw";

    private static MandrillRESTRequest request = new MandrillRESTRequest();
    private static MandrillConfiguration config = new MandrillConfiguration();
    private static MandrillMessagesRequest messagesRequest = new MandrillMessagesRequest();
    private static HttpClient client;
    private static ObjectMapper mapper = new ObjectMapper();
    private static Properties props = new Properties();

    private static MandrillMailServiceImpl mailService = null;

    @Autowired
    private UserService userService;

    //TODO add this to get real url of application
    /*
    public static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }*/


    private static void initialize(){

        config.setApiKey(API_KEY);
        config.setApiVersion("1.0");
        config.setBaseURL("https://mandrillapp.com/api");
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
        String html;
        String link = MAIN_URL + model.getPassword() + "&id=" + model.getId();
        html = String.format("" +
                "<html>" +
                "<body>" +
                "<h3>Thank you for register %s on bawl</h3>" +
                "Click on link below for confirm" +
                " <a href=%s>Bawl</a>" +
                "</body>" +
                "</html>", model.getName(), link);
        sendMessage(model.getEmail(), html);

    }



    private void sendMessage(String email, String html) {
        MandrillMessageRequest mmr = new MandrillMessageRequest();
        MandrillHtmlMessage message = new MandrillHtmlMessage();
        Map<String, String> headers = new HashMap<String, String>();
        message.setFrom_email("bawlapp@ukr.net");
        message.setFrom_name("Bawl Service");
        message.setHeaders(headers);
        message.setHtml(html);
        MandrillRecipient[] recipients = new MandrillRecipient[]{new MandrillRecipient("Admin", email)};
        message.setTo(recipients);
        message.setTrack_clicks(true);
        message.setTrack_opens(true);
        mmr.setMessage(message);

        try {
            SendMessageResponse response = messagesRequest.sendMessage(mmr);
        } catch (RequestFailedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void notifyForIssue(int issueId, String msg) {

    }

    @Override
    public void notifyUser(int userId, String msg) {
        sendMessage(userService.getById(userId).getEmail(), msg);
    }
}
