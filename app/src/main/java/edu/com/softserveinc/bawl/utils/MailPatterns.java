package edu.com.softserveinc.bawl.utils;

public class MailPatterns {

    public final static String REGISTRATION_PATTERN =
            "<html>" +
            "<body>" +
            "<h3>Thank you for register %s on bawl</h3>" +
            "Click on link below for confirm" +
            " <a href=%s>Bawl</a>" +
            "</body>" +
            "</html>";

    public final static String SUBSCRIPTION_PATTERN =
                    "<html>" +
                    "<body>" +
                    "<h3>Thank you for subscription %s on bawl</h3>" +
                    "Click on link below for confirm subscription" +
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

    public final static String UPDATE_ACCOUNT_PATTERN = "Your account has been updated.\n\nCurrent login: %s\n Current role: %s";

    public final static String DELETE_ACCOUNT_PATTERN = "Your account has been terminated";

    public final static String NOTIFY_FOR_ISSUE_PATTERN =
            "<html><body>" +
            "<h3>Bawl notification for issue #%s</h3><br/>" +
            "<p> %s </p><br />" +
            "<p>You may <a href=%s>unsubscribe</a> at any time.</p>" +
            "</body></html>";

}
