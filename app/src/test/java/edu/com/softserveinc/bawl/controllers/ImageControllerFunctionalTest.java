package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;


public class ImageControllerFunctionalTest extends AbstractBawlFunctionalTest {
    private MockMvc mockMvc;

    @InjectMocks ImageController imageController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.imageController).build();
    }

    @Test
    public void testCropImage() throws Exception {
        mockMvc.perform(post("/image/crop")).andExpect(status().isOk());
    }

}
