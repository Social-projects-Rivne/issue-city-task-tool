package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.CommentService;
import edu.com.softserveinc.bawl.services.HistoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

/**
 * Created by Vasj on 05.11.2015.
 */
public class StatisticControllerTest extends AbstractBawlTest{
    private MockMvc mockMvc;

    @InjectMocks
    private StatisticController statisticController;

    @Mock
    private CategoryService categoryService;
    @Mock
    private HistoryService historyService;
    @Mock
    private CommentService commentService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.statisticController).build();
    }

    @Test
    public void testStatisticByCategory() throws Exception {
        mockMvc.perform(post("/statistics/categories")).andExpect(status().isOk());
    }

    @Test
    public void testStatisticByStatus() throws Exception {
        mockMvc.perform(post("/statistics/statuses")).andExpect(status().isOk());
    }

    @Test
    public void testStatisticByComments() throws Exception {
        mockMvc.perform(post("/statistics/comments")).andExpect(status().isOk());
    }
}