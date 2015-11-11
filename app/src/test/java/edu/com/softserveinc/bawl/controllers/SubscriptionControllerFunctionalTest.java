package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.models.SubscriptionModel;
import edu.com.softserveinc.bawl.services.SubscriptionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class SubscriptionControllerFunctionalTest  extends AbstractBawlTest {

    private MockMvc mockMvc;

    @InjectMocks SubscriptionController subscriptionController;
    @Mock SubscriptionService subscriptionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.subscriptionController).build();
    }

    @Test
    public void testGetUserHistoryAction() throws Exception {
        mockMvc.perform(post("/subscriptions/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"id\" : \"1\",\"issueId\" : \"1\",\"email\" : \"foo@foo\"}".getBytes()))
                .andExpect(status().isOk());
        Mockito.verify(subscriptionService, Mockito.times(1)).create(1, "foo@foo");
    }

    @Test
    public void testCancelSubscription() throws Exception {
        mockMvc.perform(post("/subscriptions/1/delete/1")).andExpect(status().isOk());
        Mockito.verify(subscriptionService, Mockito.times(1)).delete(1);
    }
}
