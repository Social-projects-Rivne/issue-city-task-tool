package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.services.StatusService;
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

public class StatusControllerTest extends AbstractBawlTest {

    public static final String EMPTY_COLLECTION = "[]";
    private MockMvc mockMvc;

    @InjectMocks
    private StatusController statusController;
    @Mock
    private StatusService statusService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.statusController).build();
    }

    @Test
    public void testGetStatuses() throws Exception {
        mockMvc.perform(get("/statuses/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(EMPTY_COLLECTION));
    }

}