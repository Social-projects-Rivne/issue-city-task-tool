package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

/**
 * Created by Illia on 10/26/2015.
 */
public class HistoryControllerFunctionalTest extends AbstractBawlTest {

  private MockMvc mockMvc  = null;
  String MEDIA_TYPE = "application/json;charset=UTF-8";
  String EMPTY_COLLECTION = "[]";

  @InjectMocks
  private HistoryController historyController;
  @Mock
  private HistoryService historyService;

  @Mock
  private IssueService issueService;

  @Mock
  private UserService userService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.historyController).build();
  }

  @Test
  public void testGetUserHistoryAction() throws Exception {
    mockMvc.perform(get("/issue/1/history"))
      .andExpect(status().isOk())
      .andExpect(content().mimeType(MEDIA_TYPE))
      .andExpect(content().string(EMPTY_COLLECTION));
  }

    @Test
    public void testgetUserIssuesHistories() throws Exception {
        mockMvc.perform(get("/user/1/history"))
                .andExpect(status().isOk())
                .andExpect(content().mimeType(MEDIA_TYPE))
                .andExpect(content().string(EMPTY_COLLECTION));
    }

}
