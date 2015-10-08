package edu.com.softserveinc.bawl.models;

import java.io.Serializable;
/**
 * Created by Oleg on 08.10.2015.
 */
public class UserNotificationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String subject;
    private String message;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
