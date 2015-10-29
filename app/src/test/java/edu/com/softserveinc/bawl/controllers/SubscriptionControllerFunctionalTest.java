package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class SubscriptionControllerFunctionalTest  extends AbstractBawlTest {

    public static final String MEDIA_TYPE = "application/json;charset=UTF-8";
    public static final String EMPTY_COLLECTION = "[]";


    private MockMvc mockMvc;

    @InjectMocks
    private SubscriptionController subscriptionController;

    @Mock
    private HistoryService historyService;
    private SubscriptionService subscriptionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.subscriptionController).build();
    }

    @Test
    public void testGetUserHistoryAction() throws Exception {
        mockMvc.perform(post("/subscriptions"))
                .andExpect(status().isOk())
                .andExpect(content().mimeType(MEDIA_TYPE))
                .andExpect(content().string(EMPTY_COLLECTION));
    }

    @Test
    public void testCancelSubscription() throws Exception {
        mockMvc.perform(post("/subscriptions/1/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().mimeType(MEDIA_TYPE))
                .andExpect(content().string(EMPTY_COLLECTION));
    }
}
