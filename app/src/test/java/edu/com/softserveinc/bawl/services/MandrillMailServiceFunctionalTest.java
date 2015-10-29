package edu.com.softserveinc.bawl.services;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.response.message.MessageResponse;
import com.cribbstechnologies.clients.mandrill.model.response.message.SendMessageResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillMessagesRequest;
import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Illia on 10/28/2015.
 */
public class MandrillMailServiceFunctionalTest extends AbstractBawlTest {

    private final String EMAIL = "bawl.java@gmail.com";
    private final String MESSAGE_PATTERN = "some text";
    final int ISSUE_ID = 1;
    final String VALID_SENT_STATUS = "sent";
    final String INVALID_SENT_STATUS = "invalid";


    private MandrillMailServiceImpl mailService;
    private SubscriptionService subscriptionService;
    private MandrillMessagesRequest messagesRequest;
    MandrillHtmlMessage mandrillMessage;
    SendMessageResponse sendMessageResponse;
    MessageResponse messageResponse;

    @Before
    public void setup() {
        mailService = MandrillMailServiceImpl.getMandrillMail();
        subscriptionService = mock(SubscriptionService.class);
        messagesRequest = mock(MandrillMessagesRequest.class);
        mandrillMessage = mock(MandrillHtmlMessage.class);
        sendMessageResponse = mock(SendMessageResponse.class);
        messageResponse = mock(MessageResponse.class);
        Whitebox.setInternalState(mailService, "subscriptionService", subscriptionService);
        Whitebox.setInternalState(MandrillMailServiceImpl.class, "messagesRequest", messagesRequest);
    }

    @Test
    public void sendRegNotification_WithValidCredentials_ShouldSendNotification() throws RequestFailedException {
        when(messageResponse.getStatus()).thenReturn(VALID_SENT_STATUS);
        when(sendMessageResponse.getList()).thenReturn(Arrays.asList(messageResponse));
        when(messagesRequest.sendMessage(any(MandrillMessageRequest.class))).thenReturn(sendMessageResponse);

        mailService.sendMessage(mandrillMessage);

        verify(messagesRequest).sendMessage(any(MandrillMessageRequest.class));
    }

    @Test(expected=RequestFailedException.class)
    public void sendRegNotification_WithWrongCredentials_ThrowsException() throws RequestFailedException {
        when(messageResponse.getStatus()).thenReturn(INVALID_SENT_STATUS);
        when(sendMessageResponse.getList()).thenReturn(Arrays.asList(messageResponse));
        when(messagesRequest.sendMessage(any(MandrillMessageRequest.class))).thenReturn(sendMessageResponse);

        mailService.sendMessage(mandrillMessage);

        verify(messagesRequest).sendMessage(any(MandrillMessageRequest.class));
    }

    @Test
    public void notifyForIssue_WithValidCredentials_shouldSendNotificationToUsers() throws RequestFailedException {
        SubscriptionModel subscriptionModel = mock(SubscriptionModel.class);
        when(subscriptionModel.getIssueId()).thenReturn(ISSUE_ID);
        when(subscriptionModel.getEmail()).thenReturn(EMAIL);
        when(messageResponse.getStatus()).thenReturn(VALID_SENT_STATUS);
        when(sendMessageResponse.getList()).thenReturn(Arrays.asList(messageResponse));
        when(messagesRequest.sendMessage(any(MandrillMessageRequest.class))).thenReturn(sendMessageResponse);
        when(subscriptionService.listByIssueId(1)).thenReturn(Arrays.asList(subscriptionModel));

        mailService.notifyForIssue(ISSUE_ID ,MESSAGE_PATTERN);

        verify(messagesRequest).sendMessage(any(MandrillMessageRequest.class));
    }

    @Test(expected=RequestFailedException.class)
    public void notifyForIssue_WithWrongCredentials_shouldSendNotificationToUsers() throws RequestFailedException {
        SubscriptionModel subscriptionModel = mock(SubscriptionModel.class);
        when(subscriptionModel.getIssueId()).thenReturn(ISSUE_ID);
        when(subscriptionModel.getEmail()).thenReturn(EMAIL);
        when(messageResponse.getStatus()).thenReturn(INVALID_SENT_STATUS);
        when(sendMessageResponse.getList()).thenReturn(Arrays.asList(messageResponse));
        when(messagesRequest.sendMessage(any(MandrillMessageRequest.class))).thenReturn(sendMessageResponse);
        when(subscriptionService.listByIssueId(1)).thenReturn(Arrays.asList(subscriptionModel));

        mailService.notifyForIssue(ISSUE_ID ,MESSAGE_PATTERN);

        verify(messagesRequest).sendMessage(any(MandrillMessageRequest.class));
    }
}
