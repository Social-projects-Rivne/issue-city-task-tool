package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.MvcResult;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

/**
 * Created by Illia on 10/26/2015.
 */
public class HistoryControllerIntegrationTest extends AbstractBawlTest {

  private MockMvc mockMvc  = null;
  String MEDIA_TYPE = "application/json;charset=UTF-8";
  String EMPTY_COLLECTION = "[]";

  @Autowired
  private HistoryController historyController;

  @Before
  public void setup() {
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
