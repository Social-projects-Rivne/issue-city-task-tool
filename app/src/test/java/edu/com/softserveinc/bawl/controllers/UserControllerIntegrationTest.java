package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest extends AbstractBawlTest {

    public static final  String MEDIA_TYPE = "application/json;charset=UTF-8";
    public static final String EMPTY_COLLECTION = "[]";
    public static final int USER_ID = 1;
    public static final int ISSUE_ID = 1;
    public static final String [] FIELDS_USERHISTORY_DTO = {"username", "issueName", "date", "roleName", "statusId"};

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }


    @Test
    public void testgetUserIssuesHistories() throws Exception {
        mockMvc.perform(get(String.format("/users/%1$d/history", USER_ID)))
                .andExpect(status().isOk())
                .andExpect(content().mimeType(MEDIA_TYPE));
        //TODO check fields in result query
        //method getUserIssuesHistories() has some problems
    }
}
