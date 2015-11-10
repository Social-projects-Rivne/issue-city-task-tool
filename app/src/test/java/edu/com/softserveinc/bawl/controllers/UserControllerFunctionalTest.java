package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class UserControllerFunctionalTest extends AbstractBawlTest {


    public static final String MEDIA_TYPE = "application/json;charset=UTF-8";
    public static final String EMPTY_COLLECTION = "[]";

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private IssueService issueService;

    @Mock
    private UserService userService;

    @Mock
    private HistoryService historyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }

    @Test
    @Ignore
    public void testgetUserIssuesHistories() throws Exception {
        mockMvc.perform(get("/users/1/history"))
                .andExpect(status().isOk())
                .andExpect(content().mimeType(MEDIA_TYPE))
                .andExpect(content().string(EMPTY_COLLECTION));
    }

}
