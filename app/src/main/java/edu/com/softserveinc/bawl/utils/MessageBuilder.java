package edu.com.softserveinc.bawl.utils;

import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Illia on 10/27/2015.
 */
public class MessageBuilder {

    private String pattern = "";
    private String subject= "";
    private String message= "";
    private Properties properties;
    MandrillRecipient [] recipients;
    private Map<String, String> headers = new HashMap<String, String>();

    public MessageBuilder setPattern(String pattern, String ...params){
        message = String.format(pattern, params);
        return this;

    }

    public MessageBuilder setSubject(String subject){
        this.subject = subject;
        return this;
    }

    public MessageBuilder setHeaders(Map<String, String> headers){
        this.headers = headers;
        return this;
    }

    public MessageBuilder setRecipients(MandrillRecipient ... recipients){
        this.recipients = recipients;
        return this;
    }

    public MandrillHtmlMessage build(){
        properties = getProperties();
        MandrillHtmlMessage mandrillMessage = new MandrillHtmlMessage();
        mandrillMessage.setFrom_email(properties.getProperty("mail.from_url"));
        mandrillMessage.setFrom_name(properties.getProperty("mail.from_name"));
        mandrillMessage.setHeaders(headers);
        mandrillMessage.setSubject(subject);
        mandrillMessage.setHtml(message);
        mandrillMessage.setTo(recipients);
        mandrillMessage.setTrack_clicks(true);
        mandrillMessage.setTrack_opens(true);
        return mandrillMessage;
    }

    public static Properties getProperties() {
        Properties props = new Properties();

        try (InputStream input = MessageBuilder.class.getClassLoader().getResourceAsStream("mail.properties"))
        {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
