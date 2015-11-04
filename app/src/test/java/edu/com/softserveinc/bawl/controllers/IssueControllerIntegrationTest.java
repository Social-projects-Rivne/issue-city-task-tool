package edu.com.softserveinc.bawl.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-root-context.xml"})
public class IssueControllerIntegrationTest {

  public static final  String MEDIA_TYPE = "application/json;charset=UTF-8";
  public static final String EMPTY_COLLECTION = "[]";
  public static final int USER_ID = 1;
  public static final int ISSUE_ID = 1;
  public static final String [] FIELDS_USERHISTORY_DTO = {"username", "issueName", "date", "roleName", "statusId"};

  private MockMvc mockMvc;

  @Autowired
  private IssueController issueController;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.issueController).build();
  }

  @Test
  public void testGetUserHistoryAction() throws Exception {
    mockMvc.perform(get(String.format("/issue/%1$d/history", ISSUE_ID)))
      .andExpect(status().isOk())
      .andExpect(content().mimeType(MEDIA_TYPE))
      .andExpect(content().string(EMPTY_COLLECTION));
  }




}
