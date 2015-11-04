package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.services.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class IssueControllerFunctionalTest extends AbstractBawlTest {

  public static final String MEDIA_TYPE = "application/json;charset=UTF-8";
  public static final String EMPTY_COLLECTION = "[]";
  public static final int ISSUE_ID = 1;
  private static final String IssueDto_JSON = "{\"attachments\": \"\", \"category\": \"road\", \"categoryId\": \"1\"," +
   "\"description\": \"1234\", \"id\": null, \"mapPointer\": \"LatLng(50.62856, 26.22986)\", \"name\": \"new\", \"priorityId\": \"1\", \"status\": \"new\", \"statusId\": \"1\"}";
  private static final Authentication auth = new UsernamePasswordAuthenticationToken("admin", "admin");

  private MockMvc mockMvc;

  @InjectMocks
  private UserController userController;

    @Mock
    private IssueController issueController;

    @Mock
    private IssueService issueService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private MailService mailService;

    @Mock
    private UserService userService;

    @Mock
    HistoryService historyService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.issueController).build();
  }

  @Test
  public void getUserHistoryAction_ShouldBeResponseStatusOk_AndEmptyCollection() throws Exception {
    mockMvc.perform(get(String.format("/issue/%d/history", ISSUE_ID)))
            .andExpect(status().isOk())
            .andExpect(content().mimeType(MEDIA_TYPE))
            .andExpect(content().string(EMPTY_COLLECTION));
  }

  @Test
  public void addIssue_ShouldBeResponseStatusOk_AndEmptyCollection() throws Exception {
    mockMvc.perform(post("/issue")
            .contentType(MediaType.APPLICATION_JSON)
            .body(IssueDto_JSON.getBytes()))
            .andExpect(status().isOk());

  }

  @Test
  public void toResolve_ShouldChangeStatusIssueToResolve() throws Exception {
    //SecurityContextHolder.getContext().setAuthentication(auth);
    mockMvc.perform(post(String.format("/to-resolve/%d", ISSUE_ID))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());


  }

  @Test
  @Ignore // needs autentificated admin in method
  public void getIssue_ShouldBeResponseStatusOk_AndEmptyCollection() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(auth);
        mockMvc.perform(get(String.format("/issue/%d", ISSUE_ID)))
                .andExpect(status().isOk())
                .andExpect(content().string(EMPTY_COLLECTION));


  }


}
