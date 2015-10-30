package edu.com.softserveinc.bawl.exception;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import org.springframework.mail.MailException;

import java.io.IOException;

/**
 * Created by Illia on 10/29/2015.
 */
public class MailSendException extends RequestFailedException {

    String message;

    public MailSendException(String msg) {
        super(msg);
        this.message = msg;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
