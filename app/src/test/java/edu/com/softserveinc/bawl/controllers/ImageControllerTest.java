package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

/**
 * Created by Dominus on 28.10.2015.
 */
public class ImageControllerTest extends AbstractBawlTest {
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
