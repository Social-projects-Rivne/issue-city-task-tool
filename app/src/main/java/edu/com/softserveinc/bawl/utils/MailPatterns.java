package edu.com.softserveinc.bawl.utils;

/**
 * Created by Illia on 10/27/2015.
 */
public class MailPatterns {

    public final static String REGISTRATION_PATTERN =
            "<html>" +
            "<body>" +
            "<h3>Thank you for register %s on bawl</h3>" +
            "Click on link below for confirm" +
            " <a href=%s>Bawl</a>" +
            "</body>" +
            "</html>";

    public final static String PASSWORD_RESET_PATTERN =
            "<html>" +
            "<body>" +
            "<h3>Changed password</h3>" +
            "Now your password is %s " +
            "</body>" +
            "</html>";


}
