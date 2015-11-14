package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.MailService;
import edu.com.softserveinc.bawl.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class IssueControllerFunctionalTest extends AbstractBawlFunctionalTest {

    public static final String MEDIA_TYPE = "application/json;charset=UTF-8";
    public static final String EMPTY_COLLECTION = "[]";
    public static final int ISSUE_ID = 1;
    private static final String IssueDto_JSON = "{\"attachments\": \"\", \"category\": \"road\", \"categoryId\": \"1\"," +
            "\"description\": \"1234\", \"id\": null, \"mapPointer\": \"LatLng(50.62856, 26.22986)\", \"name\": \"new\", \"priorityId\": \"1\", \"status\": \"APPROVED\"}";

    private MockMvc mockMvc;

    @InjectMocks
    IssueController issueController;

    @Mock
    IssueService issueService;

    @Mock
    CategoryService categoryService;

    @Mock
    MailService mailService;

    @Mock
    UserService userService;

    @Mock
    HistoryService historyService;

    @Mock
    HistoryDao historyDao;

    @Before
    public void setup() {
        setupMockSecurityContext();
        MockitoAnnotations.initMocks(this);
        IssueModel issue = new IssueModel();
        issue.setId(1);
        issue.setName("foo");
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1);
        issue.setCategory(categoryModel);
        issue.setStatus(IssueStatus.APPROVED);
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setLogin("admin");
        userModel.setRole(UserRole.ADMIN);
        Mockito.when(userService.getByLogin(Mockito.anyString())).thenReturn(userModel);
        Mockito.when(this.historyService.getLastIssueByIssueID(Mockito.anyInt())).thenReturn(issue);
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
        mockMvc.perform(post(String.format("/issue/resolve/%d", ISSUE_ID))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void getIssue_ShouldBeResponseStatusOk_AndEmptyCollection() throws Exception {
        mockMvc.perform(get(String.format("/issue/%d", ISSUE_ID)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"foo\",\"description\":null,\"mapPointer\":null,\"attachments\":null,\"categoryId\":1,\"priorityId\":0,\"category\":null,\"status\":\"APPROVED\"}"));


    }

}
