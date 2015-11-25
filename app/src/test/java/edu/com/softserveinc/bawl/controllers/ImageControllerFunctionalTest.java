package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import edu.com.softserveinc.bawl.services.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;


public class ImageControllerFunctionalTest extends AbstractBawlFunctionalTest {
  private MockMvc mockMvc;

  @InjectMocks
  ImageController imageController;

  @Mock
  ImageService imageService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.imageController).build();
  }

  @Test
  public void testCropImage() throws Exception {
    mockMvc.perform(post("/image/crop")).andExpect(status().isOk());
    verify(imageService, timeout(1)).cropImage();
  }

}
